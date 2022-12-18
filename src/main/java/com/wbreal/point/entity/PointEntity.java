package com.wbreal.point.entity;

import com.wbreal.point.constants.PointActionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Entity
@Table(schema = "musinsadb" ,name="point")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "seq")
	private Long seq;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "point")
	private BigDecimal point;

	@Column(name = "remain_point")
	@Getter
	private BigDecimal remainPoint;

	@Column(name = "parent_seq")
	private Long parentSeq;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "action_type")
	private PointActionType pointActionType;

	@Column(name = "regist_date")
	private LocalDate registDate;

	@Column(name = "update_date")
	private LocalDate updateDate;

	@Column(name = "expire_date")
	private LocalDate expireDate;

}
