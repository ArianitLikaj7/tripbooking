package com.arianit.tripbooking.dao;

import com.arianit.tripbooking.entity.Trip;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
   List<Trip> findAllByCreatedBy(String createdBy);
   // Page<Trip> findAllByCreatedByPageable(String createdBy, Pageable pageable);
}
