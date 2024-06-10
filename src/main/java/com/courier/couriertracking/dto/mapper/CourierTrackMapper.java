package com.courier.couriertracking.dto.mapper;

import com.courier.couriertracking.entity.CourierTrack;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import org.springframework.stereotype.Component;

@Component
public class CourierTrackMapper {
    public CourierTrack courierLocationRequestToCourierTrack(SaveCourierLocationRequest request) {
        return CourierTrack.builder()
                .courierId(request.getCourierId())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .updatedDate(request.getCurrentTime())
                .build();
    }
}
