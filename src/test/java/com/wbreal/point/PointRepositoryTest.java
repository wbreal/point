package com.wbreal.point;

import com.wbreal.point.constants.PointActionType;
import com.wbreal.point.entity.MemberEntity;
import com.wbreal.point.entity.PointEntity;
import com.wbreal.point.repository.MemberRepository;
import com.wbreal.point.repository.PointRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // 모든 테스트 케이스마다 시작하기 이전 Context 재생성
class PointRepositoryTest {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("should find remain point by member id")
    void shouldFindRemainPointByMemberId() {

		/*
            1. 멤버 엔티티 및 데이터 생성
            2. 목표 포인트 설정
            3. 500원 적립 포인트 엔티티 생성
            4. 1000원 적립 포인트 엔티티 생성
            5. 리스트 of 메서드를 통해서 500/1000 데이터 생성
            6. 저장된 포인트 엔티티의 남은 금액이 처음에 목표한 남은 금액과 일치하는가 ?
		 */
        //Given
        LocalDateTime now = LocalDateTime.now();
        MemberEntity saveMember = this.memberRepository.save(MemberEntity.builder().registDate(now).updateDate(now).build());

        BigDecimal REMAIN_POINT = new BigDecimal(1500);

        PointEntity savePoint500 = PointEntity.builder().memberId(saveMember.getMemberId()).remainPoint(new BigDecimal(500)).point(new BigDecimal(500)).pointActionType(
                PointActionType.EARN).expireDate(now.plusYears(1L)).registDate(now).updateDate(now).build();

        PointEntity savePoint1000 = PointEntity.builder().memberId(saveMember.getMemberId()).remainPoint(new BigDecimal(1000)).point(new BigDecimal(1000)).pointActionType(
                PointActionType.EARN).expireDate(now.plusYears(1L)).registDate(now).updateDate(now).build();


        this.pointRepository.saveAll(List.of(savePoint500, savePoint1000));
        // When
        BigDecimal remainPoint = pointRepository.findRemainPointByMemberId(saveMember.getMemberId());

        // Then
        assertTrue(REMAIN_POINT.compareTo(remainPoint) == 0);
    }

    @Test
    @DisplayName("should find all point by member id order by seq")
    void shouldFindAllPointByMemberIdOrderBySeq() {
        /*
            1. 멤버 엔티티 및 데이터 생성
            2. 500/1000 포인트 적립 엔티티 생성
            3. 500/1000 포인트 엔티티 데이터 생성
            4. seq 기준 소팅 설정
            5. 페이징 시작과 사이즈, seq 기준 소팅 값 설정
            6. 설정된 페이징 변수와 회원 포인트 정보 조회
            7. seq 기준으로 소팅되어 조회되었는가 ?
            7. 3번 생성 시 반환된 엔티티 사이즈와 6번 조회한 엔티티 사이즈가 같은가 ?
               사이즈 10 이하로 설정했기 떄문에 테스트 목표 설정 가능
         */
        //Given
        LocalDateTime now = LocalDateTime.now();
        MemberEntity saveMember = this.memberRepository.save(MemberEntity.builder().registDate(now).updateDate(now).build());

        PointEntity savePoint500 = PointEntity.builder().memberId(saveMember.getMemberId()).remainPoint(new BigDecimal(500)).point(new BigDecimal(500)).pointActionType(
                PointActionType.EARN).expireDate(now.plusMonths(1L)).registDate(now).updateDate(now).build();

        PointEntity savePoint1000 = PointEntity.builder().memberId(saveMember.getMemberId()).remainPoint(new BigDecimal(1000)).point(new BigDecimal(1000)).pointActionType(
                PointActionType.EARN).expireDate(now.plusMonths(1L)).registDate(now).updateDate(now).build();

        List<PointEntity> points = this.pointRepository.saveAll(List.of(savePoint500, savePoint1000));

        // When
        Sort sort = Sort.by("seq");
        Pageable pageable = PageRequest.of(0, 10, sort);
        List<PointEntity> actual = pointRepository.findAllPointByMemberIdOrderBySeq(saveMember.getMemberId(), pageable);

        // Then
        assertTrue(actual.get(0).getSeq() < actual.get(1).getSeq());
        assertEquals(points.size(), actual.size());
    }
}
