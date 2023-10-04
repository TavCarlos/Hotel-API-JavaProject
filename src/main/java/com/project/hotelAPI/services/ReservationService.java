package com.project.hotelAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	public Reservation createReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation;
	}
	
}
