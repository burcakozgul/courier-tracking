package com.courier.couriertracking.repository;

import com.courier.couriertracking.entity.CourierTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierTrackRepository extends JpaRepository<CourierTrack, Long> {
    List<CourierTrack> findByCourierIdOrderByUpdatedDate(Long courierId);

}
