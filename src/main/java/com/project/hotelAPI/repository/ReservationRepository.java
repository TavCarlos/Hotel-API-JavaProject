package com.project.hotelAPI.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.repository.projections.ReservationProjection;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Page<ReservationProjection> findAllByClientCpf(String cpf ,Pageable pageable);
	
	Optional<Reservation> findByBookingNumber(String number);
	
}
