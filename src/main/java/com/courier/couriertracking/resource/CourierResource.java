package com.courier.couriertracking.resource;

import com.courier.couriertracking.dto.CourierDto;
import com.courier.couriertracking.model.request.CreateCourierRequest;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/couriers")
public class CourierResource {

    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<CourierDto> createCourier(@RequestBody CreateCourierRequest request) {
        CourierDto courierDto = courierService.createCourier(request);
        return new ResponseEntity<>(courierDto, HttpStatus.CREATED);
    }

    @PostMapping("/location")
    public ResponseEntity<Void> saveCourierLocation(@RequestBody SaveCourierLocationRequest request) {
        courierService.saveCourierLocation(request);
        return ResponseEntity.ok().build();
    }

}
