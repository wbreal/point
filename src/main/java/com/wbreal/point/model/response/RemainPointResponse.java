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

    public static RemainPointResponse convertTo(final Long memberId, final BigDecimal remainPoint) {
        if (remainPoint == null) {
            log.debug("사용가능한 포인트가 없는 계정입니다. [아이디 : {}, 포인트 : {}]", memberId, null);
            return builder().remainPoint(BigDecimal.ZERO).build();
        }

        // 마이너스는 시스템 오류로 고객에게 마이너스 금액을 노출할 필요는 없다.
        if (remainPoint.compareTo(BigDecimal.ZERO) < NumberUtils.INTEGER_ZERO) {
            log.error("데이터 확인이 필요한 계정입니다. [아이디 : {}, 포인트 : {}]", memberId, remainPoint);
            return builder().remainPoint(BigDecimal.ZERO).build();
        }

        return builder().remainPoint(remainPoint).build();
    }

}
