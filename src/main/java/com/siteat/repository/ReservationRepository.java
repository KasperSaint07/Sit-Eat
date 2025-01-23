package com.siteat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.siteat.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
