package com.wbreal.point.service;

import com.wbreal.point.entity.PointEntity;
import com.wbreal.point.model.request.PointRequest;
import com.wbreal.point.model.response.PointResponse;
import com.wbreal.point.repository.PointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    public BigDecimal getRemainPoint(final Long memberId) {
        return pointRepository.findRemainPointByMemberIdGroupByMemberId(memberId);
    }

    public List<PointResponse> getPoints(final Long memberId, Pageable pageable) {
        return pointRepository.findAllPointByMemberIdOrderBySeqDesc(memberId, pageable).stream().map(pointEntity -> PointResponse.convertTo(pointEntity)).collect(Collectors.toUnmodifiableList());
    }

    public PointEntity postPoint(Long memberId, PointRequest pointRequest) {
        return pointRepository.save(
                PointEntity.builder()
                        .memberId(memberId)
                        .pointActionType(pointRequest.getPointActionType())
                        .point(pointRequest.getPoint())
                        .build());
    }

    public void deletePoint(Long usedPointSequence) {
        pointRepository.deleteById(usedPointSequence);
    }
}
