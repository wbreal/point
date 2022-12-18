package com.wbreal.point.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@ToString
public class Point {
	@PositiveOrZero
	@NotNull
	@Setter
	@Getter
	private Integer point;

	public static void isAvailable(Point point) {
		if (point.getPoint() < NumberUtils.INTEGER_ZERO) throw new IllegalStateException("포인트 조회 오류 시스템 문의 필요");
	}
}
