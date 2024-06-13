package com.arianit.tripbooking.dao;

import com.arianit.tripbooking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserId(Long userId);
}
