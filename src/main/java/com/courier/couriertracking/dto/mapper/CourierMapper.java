package com.courier.couriertracking.dto.mapper;

import com.courier.couriertracking.dto.CourierDto;
import com.courier.couriertracking.entity.Courier;
import com.courier.couriertracking.model.request.CreateCourierRequest;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {
    public Courier createCourierRequestToCourier(CreateCourierRequest request) {
        return Courier.builder()
                .courierId(request.getCourierId())
                .name(request.getName())
                .build();
    }

    public CourierDto courierToCourierDto(Courier courier) {
        return CourierDto.builder()
                .courierId(courier.getCourierId())
                .name(courier.getName())
                .id(courier.getId())
                .build();
    }
}
