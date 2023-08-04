package com.project.hotelAPI.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.GuestRepository;
import com.project.hotelAPI.models.repository.ReservationRepository;
import com.project.hotelAPI.models.repository.RoomRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	GuestRepository guestRepository;
	
	

	public Reservation createReservation(String checkIn, String checkOut, int guestId, int roomId ) {
		
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


	public Iterable<Reservation> findAllReservationsByPage(int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return reservationRepository.findAll(page);
	}
	
	
	public Reservation getReservationById(int reservationId) {
		Optional<Reservation> optionalReservtion = reservationRepository.findById(reservationId);
		
		Reservation reservation = optionalReservtion.get();
		return reservation;
	}
	

	public List<Reservation> getReservationsByGuestId(int guestId) {
		Optional<Guest> optionalGuest = guestRepository.findById(guestId);
		Guest guest = optionalGuest.orElseThrow(() -> new IllegalArgumentException("Guest Id not found."));
		return guest.getReservation();
	}
	

	public List<Reservation> getReservationsByGuestCpf(String guestCpf){
		
		Optional<Guest> optionalGuest = guestRepository.findByCpf(guestCpf);
		Guest guest = optionalGuest.orElseThrow(() -> new IllegalArgumentException("Guest CPF not found."));
		return guest.getReservation();
	}


	public List<Reservation> getReservationsByRoomId(int roomId) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		Room room = optionalRoom.orElseThrow(() -> new IllegalArgumentException("Room ID not found."));
		return room.getReservation();
	}
	

	public List<Reservation> getReservationsByDateRange(String firstDate, String lastDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate startDate = LocalDate.parse(firstDate, formatter);
		LocalDate endDate = LocalDate.parse(lastDate, formatter);
		
		return reservationRepository.findByCheckInDateBetween(startDate, endDate);
		
	}
	
	
}
