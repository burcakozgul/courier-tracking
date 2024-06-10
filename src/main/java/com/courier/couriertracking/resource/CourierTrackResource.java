package com.courier.couriertracking.resource;

import com.courier.couriertracking.service.CourierTrackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/courier-tracks")
public class CourierTrackResource {

    private final CourierTrackService courierTrackService;

    @GetMapping("/total-distance/{courierId}")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable Long courierId) {
        return ResponseEntity.ok(courierTrackService.getTotalTravelDistance(courierId));
    }
}
