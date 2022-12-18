package com.wbreal.point.repository;

import com.wbreal.point.entity.MemberEntity;
import com.wbreal.point.entity.PointEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {

    List<PointEntity> findAllPointByMemberId(Long memberId);

    List<PointEntity> findAllPointByMemberIdOrderBySeqDesc(final Long memberId, final Pageable pageable);

}
