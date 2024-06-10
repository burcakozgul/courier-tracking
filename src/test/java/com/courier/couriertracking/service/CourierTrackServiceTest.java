package com.courier.couriertracking.service;

import com.courier.couriertracking.dto.mapper.CourierTrackMapper;
import com.courier.couriertracking.entity.CourierTrack;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.repository.CourierTrackRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourierTrackServiceTest {
    @InjectMocks
    private CourierTrackService courierTrackService;
    @Mock
    private CourierTrackMapper courierTrackMapper;
    @Mock
    private CourierTrackRepository courierTrackRepository;

    @Test
    void testSaveCourierTrack() {
        SaveCourierLocationRequest request = SaveCourierLocationRequest.builder()
                .courierId(1L)
                .latitude(40.9923309)
                .longitude(29.1244230)
                .currentTime(LocalDateTime.now())
                .build();

        CourierTrack courierTrack = CourierTrack.builder()
                .courierId(request.getCourierId())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .updatedDate(request.getCurrentTime())
                .build();

        when(courierTrackMapper.courierLocationRequestToCourierTrack(request)).thenReturn(courierTrack);

        courierTrackService.saveCourierTrack(request);

        verify(courierTrackRepository).save(courierTrack);
    }

    @Test
    void testGetTotalTravelDistance() {
        Long courierId = 1L;

        CourierTrack track1 = new CourierTrack();
        track1.setLatitude(40.9923307);
        track1.setLongitude(29.1244229);

        CourierTrack track2 = new CourierTrack();
        track2.setLatitude(40.9923308);
        track2.setLongitude(29.1244230);

        List<CourierTrack> courierTracks = Arrays.asList(track1, track2);

        when(courierTrackRepository.findByCourierIdOrderByUpdatedDate(anyLong())).thenReturn(courierTracks);

        Double expectedDistance = courierTrackService.calculateDistance(track1.getLatitude(), track1.getLongitude(), track2.getLatitude(), track2.getLongitude());
        Double actualDistance = courierTrackService.getTotalTravelDistance(courierId);

        assertEquals(expectedDistance, actualDistance);
    }
}