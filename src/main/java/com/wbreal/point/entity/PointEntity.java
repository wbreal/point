package com.wbreal.point.entity;

import com.wbreal.point.model.Member;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Entity
@Table(schema = "pointdb" ,name="point_entity")
public class PointEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "point_id")
	private Long pointId;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "point")
	@Getter
	private Integer point;

	@Column(name = "registration_date")
	private LocalDate registrationDate;

	@Column(name = "update_date")
	private LocalDate updateDate;



}
