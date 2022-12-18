package com.wbreal.point.controller;

import com.wbreal.point.entity.MemberEntity;
import com.wbreal.point.model.response.Point;
import com.wbreal.point.service.MemberService;
import com.wbreal.point.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberPointController {
	private final MemberService memberService;
	private final PointService pointService;

	// todo : Unit test 및 Integration test 작성

	private void isMember(Long memberId) {
		if (memberService.getMemberEntity(memberId) == null) throw new IllegalStateException("존재하지 않음");
	}
	// 회원별 포인트 합계 조회
	// 회원 별 적립금 합계는 마이너스가 될 수 없음
	@GetMapping("/{memberId}/point")
	public ResponseEntity<Point> getPoint(@PathVariable final Long memberId){
		this.isMember(memberId);
		Point point = pointService.getPoint(memberId);
		return ResponseEntity.ok(point);
	}

	// > 회원별 포인트 적립/사용 내역 조회 (페이징 처리 필수, 사용 취소된 내역은 조회되지 않음)
	@GetMapping("/{memberId}/points")
	public ResponseEntity<Point> getPoints(@PathVariable final Long memberId,
										   @PageableDefault(size = 20, sort = "update_date", direction = Sort.Direction.DESC) Pageable pageable) {
		this.isMember(memberId);
		pointService.getPoints(memberId, pageable);

		return ResponseEntity.ok().build();
	}

	// 회원별 포인트 적립
	// 적립된 포인트의 사용기간 구현 (1년)
	@PostMapping("/{memberId}/point")
	public ResponseEntity<Point> postPoint(@PathVariable final Long memberId) {
		Point point = null;
		return ResponseEntity.ok().build();
	}

	// 회원별 포인트 사용
	// 포인트 사용시 우선순위는 먼저 적립된 순서로 사용
	@PostMapping("/{memberId}/point/{usePoint}")
	public ResponseEntity<Point> postPoint(@PathVariable final Long memberId, @PathVariable final String usePoint) {
		Point point = null;
		return ResponseEntity.ok().build();
	}

	// 회원별 포인트 사용취소 API 개발 (포인트 사용 api 호출하는 쪽에서 rollback 처리를 위한 용도)
	@PutMapping("/{memberId}/point/{usedPointSequence}")
	public ResponseEntity<Point> putPoint(@PathVariable final Long memberId, @PathVariable final String usedPointSequence) {
		Point point = null;
		return ResponseEntity.ok().build();
	}

}
