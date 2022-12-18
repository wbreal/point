package com.wbreal.point.service;


import com.wbreal.point.entity.PointEntity;
import com.wbreal.point.model.response.Point;
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

    public Point getPoint(final Long memberId) {
        Point point = Point.sumConvertTo(pointRepository.findAllPointByMemberId(memberId));
        Point.isAvailable(point);
        return point;
    }

    public List<Point> getPoints(final Long memberId, Pageable pageable) {
        List<Point> points = pointRepository.findAllPointByMemberIdOrderBySeqDesc(memberId,pageable).stream().map(pointEntity -> Point.convertTo(pointEntity)).collect(Collectors.toUnmodifiableList());

        return points;
    }
}
