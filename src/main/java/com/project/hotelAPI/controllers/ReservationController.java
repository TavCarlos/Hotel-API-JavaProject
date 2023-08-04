package com.project.hotelAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.services.ReservationService;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping(path = "/add")
	public Reservation createReservation(@RequestParam String checkIn,
								@RequestParam String checkOut,
								@RequestParam int guestId, 
								@RequestParam int roomId ) {
		return reservationService.createReservation(checkIn, checkOut, guestId, roomId);
	}

	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Reservation> findAllReservationsByPage(@PathVariable int pageNumber){
		return reservationService.findAllReservationsByPage(pageNumber);
	}
	
	@GetMapping(path = "/getid/{reservationId}")
	public Reservation getReservationById(@PathVariable int reservationId) {
		return reservationService.getReservationById(reservationId);
	}
	
	@GetMapping(path = "/guets/{guestId}")
	public List<Reservation> getReservationsByGuestId(@PathVariable int guestId) {
		return reservationService.getReservationsByGuestId(guestId);
	}
	
	@GetMapping(path = "/guest/{guestCpf}")
	public List<Reservation> getReservationsByGuestCpf(@PathVariable String guestCpf){
		return reservationService.getReservationsByGuestCpf(guestCpf);
	}

	@GetMapping(path =  "/rooms/{roomId}/reservations")
	public List<Reservation> getReservationsByRoomId(@PathVariable int roomId) {
		return reservationService.getReservationsByRoomId(roomId);
	}
	
	@GetMapping(path = "/test")
	public List<Reservation> getReservationsByDateRange(@RequestParam String firstDate, @RequestParam String lastDate) {
		return reservationService.getReservationsByDateRange(firstDate, lastDate);		
	}
}
