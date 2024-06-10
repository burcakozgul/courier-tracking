package com.courier.couriertracking.service;

import com.courier.couriertracking.dto.StoreDto;
import com.courier.couriertracking.dto.mapper.StoreEntranceMapper;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.repository.StoreEntranceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreEntranceServiceTest {

    @Mock
    private StoreEntranceRepository storeEntranceRepository;

    @Mock
    private StoreEntranceMapper storeEntranceMapper;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private StoreEntranceService storeEntranceService;

    private StoreDto store1;
    private StoreDto store2;

    @BeforeEach
    void setUp() throws Exception {
        store1 = new StoreDto(1L, "Store 1", 40.9923307, 29.1244229);
        store2 = new StoreDto(2L, "Store 2", 41.0, 30.0);

        StoreDto[] storeArray = {store1, store2};
        when(objectMapper.readValue(any(InputStream.class), Mockito.eq(StoreDto[].class))).thenReturn(storeArray);

        storeEntranceService.init();
    }

    @Test
    void testCheckStoreEntranceOutsideRadius() {
        SaveCourierLocationRequest request = new SaveCourierLocationRequest(1L, 50.0, 50.0, LocalDateTime.now());

        storeEntranceService.checkStoreEntrance(request);

        verify(storeEntranceRepository, Mockito.never()).save(any());
    }

}