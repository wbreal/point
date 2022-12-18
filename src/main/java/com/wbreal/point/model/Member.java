package com.wbreal.point.model;

import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;

public class Member {

	@Min(1)
	private Long memberId;

	@Future
	private LocalDateTime updateAt;
}
