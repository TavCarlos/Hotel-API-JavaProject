package com.project.hotelAPI.models.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>, PagingAndSortingRepository<Reservation, Integer> {

	List<Reservation> findByCheckInBetween(LocalDate startDate, LocalDate endDate);
}
