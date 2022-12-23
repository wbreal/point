package com.wbreal.point.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointActionType {
    EARN,
    USE;

    // 처음에 받아서 처리하려고 크리에이터 시점을 설정했으나 api 분리로 미사용
    @JsonCreator
    @Deprecated(since = "soon", forRemoval = true)
    public static PointActionType getInstance(String s) {
        try {
            return PointActionType.valueOf(s);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

}
