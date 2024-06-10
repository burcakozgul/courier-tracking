package com.courier.couriertracking.service;

import com.courier.couriertracking.dto.StoreDto;
import com.courier.couriertracking.dto.StoreEntranceDto;
import com.courier.couriertracking.dto.mapper.StoreEntranceMapper;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.repository.StoreEntranceRepository;
import com.courier.couriertracking.util.LocationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreEntranceService {

    private final StoreEntranceRepository storeEntranceRepository;
    private final StoreEntranceMapper storeEntranceMapper;
    private final ObjectMapper objectMapper;
    @Getter
    private List<StoreDto> stores;

    @PostConstruct
    public void init() {
        try {
            InputStream inputStream = new ClassPathResource("stores.json").getInputStream();
            StoreDto[] storeArray = objectMapper.readValue(inputStream, StoreDto[].class);
            stores = Arrays.asList(storeArray);
        } catch (Exception e) {
            log.error("Error occurred while loading stores from stores.json", e);
        }
    }

    public void checkStoreEntrance(SaveCourierLocationRequest request) {
        getStores().stream()
                .filter(store -> isInsideRadiusOf100Meter(request, store) && isOneMinPassedSinceLastEntry(store.getId(), request.getCurrentTime()))
                .findFirst()
                .ifPresent(store -> {
                    saveStoreEntrance(request, store);
                    log.info("Courier {} entered store area {}", request.getCourierId(), store.getName());
                });

    }

    private void saveStoreEntrance(SaveCourierLocationRequest request, StoreDto store) {
        storeEntranceRepository.save(storeEntranceMapper.toEntity(StoreEntranceDto.builder()
                .storeId(store.getId())
                .courierId(request.getCourierId())
                .entranceTime(request.getCurrentTime())
                .build()));
    }

    private boolean isInsideRadiusOf100Meter(SaveCourierLocationRequest request, StoreDto store) {
        double distance = LocationUtils.calculateDistance(request.getLatitude(), request.getLongitude(), store.getLat(), store.getLng());
        return distance < 100;
    }

    private boolean isOneMinPassedSinceLastEntry(Long storeId, LocalDateTime currentTime) {
        return storeEntranceRepository.findFirstByStoreIdOrderByEntranceTimeDesc(storeId)
                .map(storeEntrance -> storeEntrance.getEntranceTime().plusMinutes(1).isBefore(currentTime))
                .orElse(true);
    }
}
