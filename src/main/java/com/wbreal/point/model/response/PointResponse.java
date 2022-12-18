package com.wbreal.point.model.response;

import com.wbreal.point.constants.PointActionType;
import com.wbreal.point.entity.PointEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class PointResponse {
    private Long seq;

    private Long memberId;

    private BigDecimal point;

    private BigDecimal remainPoint;

    private Long parentSeq;

    private PointActionType pointActionType;

    private LocalDate registDate;

    private LocalDate updateDate;

    private LocalDate expireDate;

    public static PointResponse convertTo(final PointEntity pointEntity) {
        return PointResponse.builder()
                .seq(pointEntity.getSeq())
                .memberId(pointEntity.getMemberId())
                .point(pointEntity.getPoint())
                .remainPoint(pointEntity.getRemainPoint())
                .parentSeq(pointEntity.getParentSeq())
                .pointActionType(pointEntity.getPointActionType())
                .registDate(pointEntity.getRegistDate())
                .updateDate(pointEntity.getUpdateDate())
                .expireDate(pointEntity.getExpireDate())
                .build();
    }

    public static PointResponse of(final Long seq) {
        return PointResponse.builder()
                .seq(seq)
                .build();
    }
}
