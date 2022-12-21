package com.wbreal.point.entity;

import com.wbreal.point.constants.PointActionType;
import com.wbreal.point.model.request.PointRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@Column(name = "member_id")
	private Long memberId;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "action_type")
	private PointActionType pointActionType;

	@Column(name = "point")
	private BigDecimal point;

	@Column(name = "remain_point")
	@Getter
	private BigDecimal remainPoint;

	@Column(name = "parent_seq")
	private Long parentSeq;

	@Column(name = "regist_date")
	private LocalDate registDate;

	@Column(name = "update_date")
	private LocalDate updateDate;

	@Column(name = "expire_date")
	private LocalDate expireDate;

	public static PointEntity convertTo(Long memberId, PointRequest pointRequest) {
		return PointEntity.builder()
				.memberId(memberId)
				.pointActionType(pointRequest.getPointActionType())
				.point(pointRequest.getPoint())
				.remainPoint(pointRequest.getPointActionType() == PointActionType.SAVE ? pointRequest.getPoint() : null)
				.parentSeq(null)
				.registDate(LocalDate.now())
				.updateDate(LocalDate.now())
				.expireDate(LocalDate.now().plusYears(1))
				.build();

	};

}
