package com.project.hotelAPI.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.enums.StatusRoom;
import com.project.hotelAPI.utils.AccomodationUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccommodationService {
	
	private final ClientService clientService;
	private final RoomService roomService;
	private final ReservationService reservationService;
	
	@Transactional
	public Reservation booking(Reservation reservation) {
		Client client = clientService.findClientByCpf(reservation.getClient().getCpf());
		reservation.setClient(client);
		
		Room room = roomService.findFreeRoom();
		room.setStatus(StatusRoom.RESERVED);
		reservation.setRoom(room);
		
		reservation.setBookingDate(LocalDateTime.now());
		reservation.setBookingNumber(AccomodationUtil.generateBookingNumber());
		
		return reservationService.createReservation(reservation);
	}

	@Transactional
	public Reservation checkIn(String bookingNumber, String cpf) {
		Reservation reservation = reservationService.findReservation(bookingNumber);
		
		if(!reservation.getClient().getCpf().equals(cpf)) {
			throw new EntityNotFoundException(String.format("No reservation found for '%s'", cpf));
		}
		
		reservation.setCheckInDate(LocalDateTime.now());
		
		return reservation;
	}
	
	@Transactional
	public Reservation checkOut(String bookingNumber) {
		Reservation reservation = reservationService.findReservation(bookingNumber);
		BigDecimal serviceCost = AccomodationUtil.generateServiceCost(reservation.getCheckInDate());
		
		reservation.setServiceCost(serviceCost);
		reservation.getRoom().setStatus(StatusRoom.FREE);
		reservation.setReceipt(AccomodationUtil.generateReceipt());
		
		return reservation;
	}
	
	@Transactional
	public void cancelling(String bookingNumber) {
		Reservation reservation = reservationService.findReservation(bookingNumber);
		BigDecimal serviceCost = AccomodationUtil.generateServiceCost(reservation.getCheckInDate());
		
		BigDecimal cancellingFee = AccomodationUtil.calculatingFee(reservation.getEstimatedCheckInDate(), serviceCost);
		reservation.setCancelationFee(cancellingFee);
	}
}
