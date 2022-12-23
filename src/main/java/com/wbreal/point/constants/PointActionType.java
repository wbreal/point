package com.wbreal.point.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointActionType {
    EARN,
    USE;

    // post 하나의 api 안에서 PointActionType 으로 분기 기능 하려고 크리에이터 시점을 설정했으나 api 분리 하는 것이
    // 더 명확한 프로세스로 판단 및 개선 진행
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
