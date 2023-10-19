package com.project.hotelAPI.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.exceptions.EntityNotFoundException;
import com.project.hotelAPI.repository.ReservationRepository;
import com.project.hotelAPI.repository.projections.ReservationProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;

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
	
	@Transactional(readOnly = true)
	public Page<ReservationProjection> findAllReservations(String cpf, Pageable pageable) {
		return reservationRepository.findAllByClientCpf(cpf, pageable);
	}
	
}
