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
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.GuestRepository;
import com.project.hotelAPI.models.repository.ReservationRepository;
import com.project.hotelAPI.models.repository.RoomRepository;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	GuestRepository guestRepository;
	
	@PostMapping(path = "/add")
	public Reservation createReservation(@RequestParam String checkIn,
								@RequestParam String checkOut,
								@RequestParam int guestId, 
								@RequestParam int roomId ) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		
		LocalDate checkInDate = LocalDate.parse(checkIn, formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOut, formatter);
		
		
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		Room room = optionalRoom.get();
		
		Optional<Guest> optionalGuest = guestRepository.findById(guestId);
		Guest guest = optionalGuest.get();
		
		Reservation reservation = new Reservation(checkInDate, checkOutDate, room, guest);
		
		reservationRepository.save(reservation);
		
		return reservation;
	}

	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Reservation> FindAllReservations(@PathVariable int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return reservationRepository.findAll(page);
	}
}
