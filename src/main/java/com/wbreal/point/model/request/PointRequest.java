package com.wbreal.point.model.request;

import com.wbreal.point.constants.PointActionType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ToString
@Getter
public class PointRequest {
	private PointActionType pointActionType;
	@NotNull
	private BigDecimal point;

}
