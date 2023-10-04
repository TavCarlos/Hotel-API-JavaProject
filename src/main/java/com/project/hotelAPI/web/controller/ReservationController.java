package com.project.hotelAPI.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.services.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation ) {
		Reservation newReservation = reservationService.createReservation(reservation);
		return ResponseEntity.ok().body(newReservation);
	}
	
}
