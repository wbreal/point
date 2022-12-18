package com.wbreal.point.controller;

import com.wbreal.point.entity.MemberEntity;
import com.wbreal.point.service.MemberService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

//	@GetMapping("/members")
//	public ResponseEntity<Collection<MemberEntity>> list(
//			@PageableDefault(size = 20, sort = "update_date", direction = Direction.DESC) Pageable pageable){
//		Collection<MemberEntity> memberEntities = memberService.getMembers(pageable);
//		return ResponseEntity.ok(memberEntities);
//	}
//
//	@PostMapping("/member")
//	public ResponseEntity<Collection<MemberEntity>> regist(final @RequestBody MemberEntity memberEntity){
////		Collection<MemberEntity> memberEntities = memberService.getMembers();
////		return null ResponseEntity.ok();
//		return null;
//	}

}
