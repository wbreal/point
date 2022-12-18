package com.wbreal.point.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ToString
public class Point {
	private Long seq;
	@NotNull
	private Long memberId;
	@PositiveOrZero
	private BigDecimal point;

	private BigDecimal remainPoint;

	private Long parentSeq;

	private PointActionType pointActionType;

	private LocalDate registDate;

	private LocalDate updateDate;

	private LocalDate expireDate;

	public static void isAvailable(final Point point) {
		if (point.getPoint().compareTo(BigDecimal.ZERO) == NumberUtils.INTEGER_ZERO) throw new IllegalArgumentException("포인트 조회 오류 시스템 문의 필요");
	}
}
