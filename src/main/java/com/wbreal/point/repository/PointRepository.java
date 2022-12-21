package com.wbreal.point.repository;

import com.wbreal.point.entity.PointEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {

    @Query(value = "SELECT sum(p.remain_point) AS remain_point " +
                     "FROM musinsadb.point p " +
                    "WHERE p.member_id = :memberId", nativeQuery = true)
    BigDecimal findRemainPointByMemberId(@Param("memberId") final Long memberId);

    List<PointEntity> findAllPointByMemberIdOrderBySeqDesc(final Long memberId, final Pageable pageable);

}
