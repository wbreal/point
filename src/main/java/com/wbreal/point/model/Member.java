package com.wbreal.point.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public class Member {

    @Min(1)
    private Long memberId;

    @Future
    private LocalDateTime updateAt;
}
