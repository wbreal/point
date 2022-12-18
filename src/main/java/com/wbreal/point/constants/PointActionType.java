package com.wbreal.point.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum PointActionType {
    USE("사용"),
    SAVE("적립");

    private String text;

}
