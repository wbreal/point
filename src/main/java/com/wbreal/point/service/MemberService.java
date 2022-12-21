package com.wbreal.point.service;


import com.wbreal.point.entity.MemberEntity;
import com.wbreal.point.repository.MemberRepository;
import java.util.Collection;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class MemberService {
	private final MemberRepository memberRepository;
	public Collection<MemberEntity> getMembers(final Pageable pageable){
//		return memberRepository.findAllBy(pageable);
		return null;
	}

	public MemberEntity register(@Valid final MemberEntity memberEntity){
		return memberRepository.save(memberEntity);
	}

	public MemberEntity getMemberEntity(Long memberId) {
		return memberRepository.findByMemberId(memberId);
	}

	public boolean existsById(Long memberId) {
		return memberRepository.existsById(memberId);
	}

}
