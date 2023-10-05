package com.project.hotelAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.repository.ReservationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Transactional
	public Reservation createReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation;
	}
	
	@Transactional(readOnly = true)
	public Reservation findReservation(String BookingNumber) {
		return reservationRepository.findByBookingNumber(BookingNumber).orElseThrow(
				() -> new EntityNotFoundException("Reservation not found"));
	}
	
}
