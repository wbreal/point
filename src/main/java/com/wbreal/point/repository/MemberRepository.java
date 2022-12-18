package com.wbreal.point.repository;

import com.wbreal.point.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByMemberId(Long memberId);

//    public MemberEntity findAllByMemberId

}
