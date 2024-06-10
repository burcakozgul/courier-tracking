package com.courier.couriertracking.repository;

import com.courier.couriertracking.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByCourierId(String courierId);
}
