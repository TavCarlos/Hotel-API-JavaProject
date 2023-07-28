package com.project.hotelAPI.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>, PagingAndSortingRepository<Reservation, Integer> {

}
