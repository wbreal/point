package com.wbreal.point.entity;

import com.wbreal.point.constants.PointActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Entity
@Table(schema = "musinsadb", name = "point")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {
    @Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "member_id")
    private Long memberId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "action_type")
    private PointActionType pointActionType;

    @Column(name = "point")
    private BigDecimal point;

    @Column(name = "remain_point")
    private BigDecimal remainPoint;

    @Column(name = "parent_seq")
    private Long parentSeq;

    @Column(name = "regist_date")
    private LocalDate registDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    public static PointEntity earnInsertOf(final Long memberId, final BigDecimal earnPoint) {
        return PointEntity.builder()
                .memberId(memberId)
                .pointActionType(PointActionType.EARN)
                .point(earnPoint)
                .remainPoint(earnPoint)
                .parentSeq(null)
                .registDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .expireDate(LocalDate.now().plusYears(1L))
                .build();
    }

    ;

    public static PointEntity useUpdateOf(final PointEntity pointEntity, final BigDecimal remainPoint) {
        return PointEntity.builder()
                .seq(pointEntity.getSeq())
                .memberId(pointEntity.getMemberId())
                .pointActionType(pointEntity.getPointActionType())
                .point(pointEntity.getPoint())
                .remainPoint(remainPoint)
                .parentSeq(null)
                .registDate(pointEntity.getRegistDate())
                .updateDate(pointEntity.getUpdateDate())
                .expireDate(pointEntity.getExpireDate())
                .build();
    }

    public static PointEntity useInsertOf(final Long memberId, final BigDecimal usePoint, final Long parentSeq) {
        return PointEntity.builder()
                .memberId(memberId)
                .pointActionType(PointActionType.USE)
                .point(usePoint)
                .remainPoint(null)
                .parentSeq(parentSeq)
                .registDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .expireDate(null)
                .build();
    }

    ;

}
