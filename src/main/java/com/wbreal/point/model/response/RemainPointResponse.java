package com.wbreal.point.model.response;

import lombok.Builder;
import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

@ToString
@Builder
public class RemainPointResponse {
	private BigDecimal availablePoint;

	public static RemainPointResponse convertTo(final BigDecimal remainPoint) {
		if (remainPoint.compareTo(BigDecimal.ZERO) == NumberUtils.INTEGER_ZERO)
			throw new IllegalStateException("사용가능한 포인트가 없습니다.");

		return builder().availablePoint(remainPoint).build();
	}

}
