package com.wbreal.point.entity;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Entity
@Table(schema = "pointdb" ,name="member_entity")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "registration_date")
	private LocalDate registrationDate;

	@Column(name = "update_date")
	private LocalDate updateDate;

}