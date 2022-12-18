package com.wbreal.point.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum PointActionType {
    USE("사용"),
    SAVE("적립");

    private String text;

    @JsonCreator
    public static PointActionType getInstance(String s) {
        try {
            return PointActionType.valueOf(s);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

}
