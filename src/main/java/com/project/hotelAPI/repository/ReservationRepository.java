package com.project.hotelAPI.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long>, PagingAndSortingRepository<Reservation, Long> {

	List<Reservation> findByCheckInDateBetween(LocalDate startDate, LocalDate endDate);
	
	
}
