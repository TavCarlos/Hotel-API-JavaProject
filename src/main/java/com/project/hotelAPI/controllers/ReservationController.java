package com.project.hotelAPI.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.models.repository.GuestRepository;
import com.project.hotelAPI.models.repository.ReservationRepository;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	GuestRepository guestRepository;
	
	@PostMapping(path = "/add")
	public Reservation createReservation(@RequestParam String checkIn, @RequestParam String checkOut, @RequestParam String guestId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		
		LocalDate checkInDate = LocalDate.parse(checkIn, formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOut, formatter);
		
		Optional<Guest> guest =  guestRepository.findById(guestId);
		Reservation newReservation = new Reservation(checkInDate, checkOutDate);
		newReservation.setGuest(guest.get());
		
		reservationRepository.save(newReservation);
		return newReservation;
	}
	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Reservation> FindAllReservations(@PathVariable int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return reservationRepository.findAll(page);
	}
}
