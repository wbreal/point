package com.wbreal.point.model.request;

import com.wbreal.point.constants.PointActionType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@ToString
@Getter
public class PointRequest {
	@NotNull
	@Positive
	private BigDecimal point;

}
