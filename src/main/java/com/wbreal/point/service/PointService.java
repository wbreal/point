package com.wbreal.point.service;

import com.wbreal.point.entity.PointEntity;
import com.wbreal.point.model.request.PointRequest;
import com.wbreal.point.model.response.PointResponse;
import com.wbreal.point.model.response.RemainPointResponse;
import com.wbreal.point.repository.PointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
        for (PointEntity pointEntity : pointRepository.findAllPointByMemberIdOrderBySeqDesc(memberId, pageable)) {
            PointResponse pointResponse = PointResponse.convertTo(pointEntity);
            pointResponses.add(pointResponse);
        }
        if (CollectionUtils.isEmpty(pointResponses)) return null;
        return pointResponses;
    }

    public PointResponse postPoint(Long memberId, PointRequest pointRequest) {
        return PointResponse.convertTo(pointRepository.save(PointEntity.convertTo(memberId, pointRequest)));
    }

    public void deletePoint(Long seq) {
        pointRepository.deleteById(seq);
    }
}
