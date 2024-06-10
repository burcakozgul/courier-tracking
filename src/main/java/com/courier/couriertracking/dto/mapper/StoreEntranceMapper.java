package com.courier.couriertracking.dto.mapper;

import com.courier.couriertracking.dto.StoreEntranceDto;
import com.courier.couriertracking.entity.StoreEntrance;
import org.springframework.stereotype.Component;

@Component
public class StoreEntranceMapper {
    public StoreEntrance toEntity(StoreEntranceDto storeEntranceDto) {
        return StoreEntrance.builder()
                .courierId(storeEntranceDto.getStoreId())
                .storeId(storeEntranceDto.getStoreId())
                .entranceTime(storeEntranceDto.getEntranceTime())
                .build();
    }
}
