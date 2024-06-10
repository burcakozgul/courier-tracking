package com.courier.couriertracking.service;

import com.courier.couriertracking.dto.CourierDto;
import com.courier.couriertracking.dto.mapper.CourierMapper;
import com.courier.couriertracking.entity.Courier;
import com.courier.couriertracking.exception.CourierAlreadyExistsException;
import com.courier.couriertracking.exception.CourierNotFoundException;
import com.courier.couriertracking.model.request.CreateCourierRequest;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;
    private final CourierTrackService courierTrackService;
    private final StoreEntranceService storeEntranceService;

    public CourierDto createCourier(CreateCourierRequest request) {
        courierRepository.findByCourierId(request.getCourierId())
                .ifPresent(courier -> {
                    throw new CourierAlreadyExistsException("Courier already exists");
                });
        Courier courier = courierRepository.save(courierMapper.createCourierRequestToCourier(request));
        log.info("Courier saved: {}", courier);
        return courierMapper.courierToCourierDto(courier);
    }

    @Transactional
    public void saveCourierLocation(SaveCourierLocationRequest request) {
        courierRepository.findById(request.getCourierId())
                .orElseThrow(() -> new CourierNotFoundException("Courier not found"));
        courierTrackService.saveCourierTrack(request);
        storeEntranceService.checkStoreEntrance(request);
    }
}
