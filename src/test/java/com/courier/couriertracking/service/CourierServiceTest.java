package com.courier.couriertracking.service;

import com.courier.couriertracking.dto.mapper.CourierMapper;
import com.courier.couriertracking.entity.Courier;
import com.courier.couriertracking.model.request.CreateCourierRequest;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourierServiceTest {
    @InjectMocks
    private CourierService courierService;
    @Mock
    private CourierTrackService courierTrackService;
    @Mock
    private StoreEntranceService storeEntranceService;
    @Mock
    private CourierMapper courierMapper;
    @Mock
    private CourierRepository courierRepository;

    @Test
    public void testCreateCourier() {
        CreateCourierRequest request = CreateCourierRequest.builder()
                .courierId("12321312434")
                .name("test")
                .build();

        when(courierMapper.createCourierRequestToCourier(request)).thenReturn(new Courier());

        courierService.createCourier(request);

        verify(courierRepository).save(Mockito.any());
    }

    @Test
    public void testSaveCourierLocation() {
        SaveCourierLocationRequest request = SaveCourierLocationRequest.builder()
                .courierId(1L)
                .latitude(40.9923309)
                .longitude(29.1244230)
                .currentTime(LocalDateTime.now())
                .build();

        when(courierRepository.findById(1L)).thenReturn(Optional.of(new Courier()));

        courierService.saveCourierLocation(request);

        verify(courierTrackService).saveCourierTrack(request);
        verify(storeEntranceService).checkStoreEntrance(request);
    }
}