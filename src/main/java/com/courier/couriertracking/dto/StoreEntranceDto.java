package com.courier.couriertracking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntranceDto {
    private Long courierId;
    private Long storeId;
    private LocalDateTime entranceTime;
}
