package com.wbreal.point.repository;

import com.wbreal.point.constants.PointActionType;
import com.wbreal.point.entity.PointEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {

    @Query(value = "SELECT sum(p.remain_point) AS remain_point " +
                     "FROM db.point p " +
                    "WHERE p.member_id = :memberId", nativeQuery = true)
    BigDecimal findRemainPointByMemberId(@Param("memberId") final Long memberId);

    List<PointEntity> findAllPointByMemberIdOrderBySeq(final Long memberId, final Pageable pageable);

    // 리소스 낭비 및 성능저하 판단으로 기능을 쿼리로 처리 -> findAllByMemberIdAndRemainPointGreaterThanAndExpireDateAfterAndPointActionTypeOrderByExpireDate
    List<PointEntity> findAllByMemberIdOrderByExpireDate(final Long memberId);

    List<PointEntity> findAllByMemberIdAndRemainPointGreaterThanAndExpireDateAfterAndPointActionTypeOrderByExpireDate(final Long memberId, final BigDecimal remainPoint, final LocalDateTime expireDate, final PointActionType pointActionType);
}
