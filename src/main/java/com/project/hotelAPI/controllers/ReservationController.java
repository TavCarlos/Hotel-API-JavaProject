package com.project.hotelAPI.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
	public Iterable<Reservation> findAllReservations(@PathVariable int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return reservationRepository.findAll(page);
	}
	
	@GetMapping(path = "/getid/{reservationId}")
	public Reservation getReservationById(@PathVariable int reservationId) {
		Optional<Reservation> optionalReservtion = reservationRepository.findById(reservationId);
		
		Reservation reservation = optionalReservtion.get();
		return reservation;
	}
	
	@GetMapping(path = "/guets/{guestId}")
	public List<Reservation> getReservationsByGuest(@PathVariable int guestId) {
		Optional<Guest> optionalGuest = guestRepository.findById(guestId);
		Guest guest = optionalGuest.orElseThrow(() -> new IllegalArgumentException("Guest Id not found."));
		return guest.getReservation();
	}
	
	@GetMapping(path = "/guest/{guestCpf}")
	public List<Reservation> getReservationsByCpf(@PathVariable String guestCpf){
		
		Optional<Guest> optionalGuest = guestRepository.findByCpf(guestCpf);
		Guest guest = optionalGuest.orElseThrow(() -> new IllegalArgumentException("Guest CPF not found."));
		return guest.getReservation();
	}

	@GetMapping(path =  "/rooms/{roomId}/reservations")
	public List<Reservation> getReservationByRoom(@PathVariable int roomId) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		Room room = optionalRoom.orElseThrow(() -> new IllegalArgumentException("Room ID not found."));
		return room.getReservation();
	}
	
	@GetMapping(path = "/test")
	public List<Reservation> getReservationByDate(@RequestParam String firstDate, @RequestParam String lastDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate startDate = LocalDate.parse(firstDate, formatter);
		LocalDate endDate = LocalDate.parse(lastDate, formatter);
		
		return reservationRepository.findByCheckInBetween(startDate, endDate);
		
	}
}
