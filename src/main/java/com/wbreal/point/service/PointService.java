package com.wbreal.point.service;

import com.wbreal.point.constants.PointActionType;
import com.wbreal.point.entity.PointEntity;
import com.wbreal.point.model.response.PointResponse;
import com.wbreal.point.model.response.RemainPointResponse;
import com.wbreal.point.repository.PointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    public RemainPointResponse getRemainPoint(final Long memberId) {
        return RemainPointResponse.convertTo(pointRepository.findRemainPointByMemberId(memberId));
    }

    public List<PointResponse> getPoints(final Long memberId, Pageable pageable) {
        List<PointResponse> pointResponses = new ArrayList<>();
        for (PointEntity pointEntity : pointRepository.findAllPointByMemberIdOrderBySeq(memberId, pageable)) {
            PointResponse pointResponse = PointResponse.selectOf(pointEntity);
            pointResponses.add(pointResponse);
        }
        if (CollectionUtils.isEmpty(pointResponses)) return null;
        return pointResponses;
    }

    public PointResponse postPointEarn(Long memberId, BigDecimal earnPoint) {
        return PointResponse.selectOf(pointRepository.save(PointEntity.earnInsertOf(memberId, earnPoint)));
    }

    public List<PointResponse> postPointUse(final Long memberId, final BigDecimal usePoint) {
        final BigDecimal remainPoints = pointRepository.findRemainPointByMemberId(memberId);
        if (remainPoints.compareTo(usePoint) < 0) throw new IllegalStateException("사용 가능한 포인트가 없습니다.");

        List<PointEntity> pointEntities = pointRepository.findAllByMemberIdOrderByExpireDate(memberId)
                .stream()
                .filter(pointEntity -> pointEntity.getPointActionType() == PointActionType.EARN
                        && pointEntity.getExpireDate().isAfter(LocalDate.now())
                        && pointEntity.getRemainPoint().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());

        List<PointResponse> pointResponses = new ArrayList<>();
        BigDecimal subtractRemainPoint = usePoint; // 500
        for (PointEntity pointEntity : pointEntities) { // 800 // 500
            BigDecimal remainPoint = pointEntity.getRemainPoint().subtract(subtractRemainPoint);
            pointResponses.add(
                    PointResponse.selectOf(
                            pointRepository.save(
                                    PointEntity.useInsertOf(memberId, subtractRemainPoint, pointEntity.getSeq()))));

            if(remainPoint.compareTo(BigDecimal.ZERO) < 0) {
                subtractRemainPoint = remainPoint;
                remainPoint = BigDecimal.ZERO;
            }
            pointRepository.save(PointEntity.useUpdateOf(pointEntity, remainPoint));
            // 더 이상 차감해야할 포인트가 없는 경우 중단
            if (subtractRemainPoint.compareTo(BigDecimal.ZERO) >= 0) break;
        }

        return pointResponses;
    }


    public void deletePoint(Long seq) {
        pointRepository.deleteById(seq);
    }
}
