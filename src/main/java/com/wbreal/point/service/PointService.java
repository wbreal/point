package com.wbreal.point.service;


import com.wbreal.point.entity.PointEntity;
import com.wbreal.point.model.response.Point;
import com.wbreal.point.repository.PointRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    public Point getPoint(Long memberId) {
        List<PointEntity> pointEntityList = pointRepository.findPointEntitiesByMemberId(memberId);
        log.info("pointEntityList :::: {}", pointEntityList.stream().toList());
        Point point = new Point();
        point.setPoint(pointEntityList.stream().mapToInt(PointEntity::getPoint).sum());
        log.info("point :::: {}", point.toString());
        Point.isAvailable(point);
        return point;
    }
}
