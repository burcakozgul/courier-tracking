package com.courier.couriertracking.repository;

import com.courier.couriertracking.entity.StoreEntrance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreEntranceRepository extends JpaRepository<StoreEntrance, Long> {
    Optional<StoreEntrance> findFirstByStoreIdOrderByEntranceTimeDesc(Long storeId);
}
