package com.courier.couriertracking.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveCourierLocationRequest {
    private Long courierId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime currentTime;

}
