package com.project.hotelAPI.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.repository.ReservationRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	ClientService guestService;
	

	public Reservation createReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation;
	}


	public Iterable<Reservation> findAllReservationsByPage(int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return reservationRepository.findAll(page);
	}
	
	
	public Reservation getReservationById(long id) {
		return reservationRepository.findById(id).orElseThrow(() 
				-> new EntityNotFoundException("Reservation ID " + id + " not found"));
	}

	public List<Reservation> getReservationsByRoomId(long id) {
		Room room = roomService.findRoomById(id);
		return room.getReservation();
	}
	

	public List<Reservation> getReservationsByDateRange(String firstDate, String lastDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate startDate = LocalDate.parse(firstDate, formatter);
		LocalDate endDate = LocalDate.parse(lastDate, formatter);
		
		return reservationRepository.findByCheckInDateBetween(startDate, endDate);
	}
	
	
	public Reservation updateReservation(Reservation reservation) {
		Reservation updatedReservation = getReservationById(reservation.getId());
		reservationRepository.save(updatedReservation);
		return updatedReservation;
	}
	
	
}
