package com.project.hotelAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.services.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping(path = "/add")
	public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation ) {
		Reservation newReservation = reservationService.createReservation(reservation);
		return ResponseEntity.ok().body(newReservation);
	}
	
	@GetMapping(path = "/{pageNumber}")
	public ResponseEntity<Iterable<Reservation>> findAllReservationsByPage(@PathVariable int pageNumber){
		Iterable<Reservation> reservations = reservationService.findAllReservationsByPage(pageNumber);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(path = "/getid/{reservationId}")
	public ResponseEntity<Reservation> getReservationById(@PathVariable int reservationId) {
		 Reservation reservation = reservationService.getReservationById(reservationId);
		 return ResponseEntity.ok().body(reservation);
	}
	
	@GetMapping(path = "/guets/{guestId}")
	public ResponseEntity<List<Reservation>> getReservationsByGuestId(@PathVariable int id) {
		List<Reservation> reservations = reservationService.getReservationsByGuestId(id);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(path = "/guest/{guestCpf}")
	public ResponseEntity<List<Reservation>> getReservationsByGuestCpf(@PathVariable String cpf){
		List<Reservation> reservations = reservationService.getReservationsByGuestCpf(cpf);
		return ResponseEntity.ok().body(reservations);
	}

	@GetMapping(path =  "/rooms/{roomId}/reservations")
	public ResponseEntity<List<Reservation>> getReservationsByRoomId(@PathVariable int roomId) {
		List<Reservation> reservations = reservationService.getReservationsByRoomId(roomId);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(path = "/test")
	public ResponseEntity<List<Reservation>> getReservationsByDateRange(@RequestParam String firstDate, @RequestParam String lastDate) {
		List<Reservation> reservations = reservationService.getReservationsByDateRange(firstDate, lastDate);
		return ResponseEntity.ok().body(reservations);
	}
}
