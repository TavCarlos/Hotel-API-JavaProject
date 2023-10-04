package com.project.hotelAPI.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.enums.StatusRoom;
import com.project.hotelAPI.utils.AccomodationUtil;

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

}
