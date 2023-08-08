package com.project.hotelAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Reservation;
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
	
	@GetMapping(path = "/page/{page}")
	public ResponseEntity<Iterable<Reservation>> findAllReservationsByPage(@PathVariable int page){
		Iterable<Reservation> reservations = reservationService.findAllReservationsByPage(page);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(path = "/{reservationId}")
	public ResponseEntity<Reservation> getReservationById(@PathVariable long reservationId) {
		 Reservation reservation = reservationService.getReservationById(reservationId);
		 return ResponseEntity.ok().body(reservation);
	}
	
	@GetMapping(path = "/guestReservations")
	public ResponseEntity<List<Reservation>> getReservationsByGuestId(@RequestParam(name = "guestId") long guestId) {
		List<Reservation> reservations = reservationService.getReservationsByGuestId(guestId);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(path = "/guest")
	public ResponseEntity<List<Reservation>> getReservationsByGuestCpf(@RequestParam(name = "cpf") String cpf){
		List<Reservation> reservations = reservationService.getReservationsByGuestCpf(cpf);
		return ResponseEntity.ok().body(reservations);
	}

	@GetMapping(path =  "/room/reservations")
	public ResponseEntity<List<Reservation>> getReservationsByRoomId(@RequestParam(name = "roomId") long roomId) {
		List<Reservation> reservations = reservationService.getReservationsByRoomId(roomId);
		return ResponseEntity.ok().body(reservations);
	}
	
	@GetMapping(path = "/date")
	public ResponseEntity<List<Reservation>> getReservationsByDateRange(@RequestParam(name = "fristDate") String firstDate,
																		@RequestParam(name = "lastDate") String lastDate) {
		List<Reservation> reservations = reservationService.getReservationsByDateRange(firstDate, lastDate);
		return ResponseEntity.ok().body(reservations);
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<Reservation> updateReservation(@Valid @RequestBody Reservation reservation) {
		Reservation updatedReservation =reservationService.updateReservation(reservation);
		return ResponseEntity.ok().body(updatedReservation);
	}
	
	
	@DeleteMapping(path = "/{guestId}/reservations/{reservationId}")
	public ResponseEntity<String> deleteReservationByGuest(@PathVariable long guestId, 
																@PathVariable long reservationId) {
		reservationService.deleteReservationByGuest(guestId, reservationId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
