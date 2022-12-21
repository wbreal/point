package com.wbreal.point.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
@Slf4j
public class RemainPointResponse {
	private BigDecimal remainPoint;

	public static RemainPointResponse convertTo(final BigDecimal remainPoint) {
		if (remainPoint == null)
			return builder().remainPoint(BigDecimal.ZERO).build();

		if (remainPoint.compareTo(BigDecimal.ZERO) < NumberUtils.INTEGER_ZERO)
			return builder().remainPoint(BigDecimal.ZERO).build();

		return builder().remainPoint(remainPoint).build();
	}

}
