package com.courier.couriertracking.service;

import com.courier.couriertracking.dto.mapper.CourierTrackMapper;
import com.courier.couriertracking.entity.CourierTrack;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.repository.CourierTrackRepository;
import com.courier.couriertracking.util.LocationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierTrackService {

    private final CourierTrackRepository courierTrackRepository;
    private final CourierTrackMapper courierTrackMapper;

    public void saveCourierTrack(SaveCourierLocationRequest request) {
        CourierTrack courierTrack = courierTrackMapper.courierLocationRequestToCourierTrack(request);
        courierTrackRepository.save(courierTrack);
        log.info("Courier location saved: {}", courierTrack);
    }

    public Double getTotalTravelDistance(Long courierId) {
        List<CourierTrack> courierTracks = courierTrackRepository.findByCourierIdOrderByUpdatedDate(courierId);
        double totalDistance = 0.0;

        for (int i = 0; i < courierTracks.size() - 1; i++) {
            CourierTrack currentLocation = courierTracks.get(i);
            CourierTrack nextLocation = courierTracks.get(i + 1);

            totalDistance += calculateDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    nextLocation.getLatitude(), nextLocation.getLongitude());
        }

        return totalDistance;
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        return LocationUtils.calculateDistance(lat1, lon1, lat2, lon2);
    }
}
