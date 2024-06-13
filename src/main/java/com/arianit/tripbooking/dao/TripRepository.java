package com.arianit.tripbooking.dao;

import com.arianit.tripbooking.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
