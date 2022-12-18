package com.wbreal.point.controller;

import com.wbreal.point.model.request.PointRequest;
import com.wbreal.point.model.response.PointResponse;
import com.wbreal.point.model.response.RemainPointResponse;
import com.wbreal.point.service.MemberService;
import com.wbreal.point.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberPointController {
    private final MemberService memberService;
    private final PointService pointService;

    // todo : Unit test 및 Integration test 작성

    private void isMember(Long memberId) {
        // todo : valid 로
        if (memberService.getMemberEntity(memberId) == null) throw new IllegalStateException("신규가입 필요");
    }

    // 회원별 포인트 합계 조회
    // 회원 별 적립금 합계는 마이너스가 될 수 없음
    @GetMapping("/{memberId}/point")
    public ResponseEntity<RemainPointResponse> getPoint(@PathVariable final Long memberId) {
        return ResponseEntity.ok(RemainPointResponse.convertTo(pointService.getRemainPoint(memberId)));
    }

    // 회원별 포인트 적립/사용 내역 조회 (페이징 처리 필수, 사용 취소된 내역은 조회되지 않음)
    // 취소 이력이 필요하면 살려둘텐데 요청과제에는 의미가 없으니 삭제
    @GetMapping("/{memberId}/points")
    public ResponseEntity<List<PointResponse>> getPoints(@PathVariable final Long memberId,
                                                         @PageableDefault(size = 20, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(pointService.getPoints(memberId, pageable));
    }

    // 회원별 포인트 적립
    // 적립된 포인트의 사용기간 구현 (1년)
    @PostMapping("/{memberId}/point")
    public ResponseEntity<PointResponse> postPoint(@PathVariable final Long memberId,
                                                   @RequestBody @Validated PointRequest pointRequest) {
        return ResponseEntity.ok(PointResponse.convertTo(pointService.postPoint(memberId, pointRequest)));
    }

    // 회원별 포인트 사용
    // 포인트 사용시 우선순위는 먼저 적립된 순서로 사용
    // 적립된 포인트를 사용한다는 개념으로 PUT을 사용했으나 실제 데이터 관리는 insert
//    @PutMapping("/{memberId}/point/{usePoint}")
//    public ResponseEntity<PointResponse> putPoint(@PathVariable final Long memberId, @PathVariable final BigDecimal usePoint) {
//        return ResponseEntity.ok(PointResponse.convertTo(pointService.putPoint(memberId, usePoint)));
//    }

    // 회원별 포인트 사용취소 API 개발 (포인트 사용 api 호출하는 쪽에서 rollback 처리를 위한 용도)
    @DeleteMapping("/{memberId}/point/{usedPointSequence}")
    public ResponseEntity<PointResponse> deletePoint(@PathVariable final Long memberId,
                                                     @PathVariable final Long usedPointSequence) {
        pointService.deletePoint(usedPointSequence);

        return ResponseEntity.ok().build();
    }

}
