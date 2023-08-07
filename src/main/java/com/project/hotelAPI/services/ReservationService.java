package com.project.hotelAPI.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.ReservationRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestService guestService;
	

	public Reservation createReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation;
	}


	public Iterable<Reservation> findAllReservationsByPage(int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return reservationRepository.findAll(page);
	}
	
	
	public Reservation getReservationById(int id) {
		return reservationRepository.findById(id).orElseThrow(() 
				-> new EntityNotFoundException("Reservation ID " + id + " not found"));
	}
	

	public List<Reservation> getReservationsByGuestId(int id) {
		Guest guest = guestService.findGuestById(id);
		return guest.getReservation();
	}
	

	public List<Reservation> getReservationsByGuestCpf(String cpf){
		Guest guest = guestService.findGuestByCpf(cpf);
		return guest.getReservation();
	}


	public List<Reservation> getReservationsByRoomId(int id) {
		Room room = roomService.findRoomById(id);
		return room.getReservation();
	}
	

	public List<Reservation> getReservationsByDateRange(String firstDate, String lastDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate startDate = LocalDate.parse(firstDate, formatter);
		LocalDate endDate = LocalDate.parse(lastDate, formatter);
		
		return reservationRepository.findByCheckInDateBetween(startDate, endDate);
	}
	
	
}
