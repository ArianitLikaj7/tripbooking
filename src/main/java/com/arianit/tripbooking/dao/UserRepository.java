package com.arianit.tripbooking.dao;

import com.arianit.tripbooking.entity.Trip;
import com.arianit.tripbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT t FROM Trip t JOIN t.reservations r WHERE r.user.id = :userId")
    List<Trip> findMyReservations(@Param("userId") Long userId);
}
