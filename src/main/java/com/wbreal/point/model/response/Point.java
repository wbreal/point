package com.wbreal.point.model.response;

import com.wbreal.point.constants.PointActionType;
import com.wbreal.point.entity.PointEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ToString
@Builder
@Getter
public class Point {
	private Long seq;

	private Long memberId;

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

	public static Point sumConvertTo(final List<PointEntity> pointEntities) {
//		Point.builder().point(pointEntities.stream().map(pointEntity -> Point.convertTo(pointEntity).getPoint()).reduce(BigDecimal.ZERO, BigDecimal::add)).build();
		return Point.builder().point(pointEntities.stream().map(pointEntity -> pointEntity.getPoint()).reduce(BigDecimal.ZERO, BigDecimal::add)).build();
	}

	public static Point convertTo(final PointEntity pointEntity) {
		return Point.builder().seq(pointEntity.getSeq())
				.point(pointEntity.getPoint())
				.remainPoint(pointEntity.getRemainPoint())
				.build();
	}

	public static PointEntity convertoFrom(final Point point){
		//~~~`
		return PointEntity.builder().build();
	}

}
