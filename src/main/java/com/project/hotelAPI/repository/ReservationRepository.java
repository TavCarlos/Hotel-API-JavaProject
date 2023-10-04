package com.project.hotelAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hotelAPI.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	
	
	
}
