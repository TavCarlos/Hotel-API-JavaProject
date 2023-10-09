package com.project.hotelAPI.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.services.AccommodationService;
import com.project.hotelAPI.web.dto.ReservationRequestDTO;
import com.project.hotelAPI.web.dto.ReservationResponseDTO;
import com.project.hotelAPI.web.dto.mapper.ReservationMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/reservations")
public class ReservationController {

	private final AccommodationService accommodationService;
	
	@PostMapping("/create")
	public ResponseEntity<ReservationResponseDTO> checkIn(@Valid @RequestBody ReservationRequestDTO reservationDto) {
		Reservation Reservation = accommodationService.booking(ReservationMapper.toReservationClass(reservationDto));

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{bookingNumber}")
				.buildAndExpand(Reservation.getBookingNumber()).toUri();
		
		return ResponseEntity.created(location).body(ReservationMapper.toDto(Reservation));
	}
	
	@PatchMapping("/checkOut/{bookingNumber}")
	public ResponseEntity<ReservationResponseDTO> checkOut(@PathVariable String bookingNumber){
		Reservation reservation = accommodationService.checkOut(bookingNumber);
		
		return ResponseEntity.ok().body(ReservationMapper.toDto(reservation));
	}
	
	@PatchMapping("/cancel/{bookingNumber}")
	public ResponseEntity<ReservationResponseDTO> cancelBooking(@PathVariable String bookingNumber){
		Reservation reservation = accommodationService.cancelling(bookingNumber);
		
		return ResponseEntity.ok().body(ReservationMapper.toDto(reservation));
	}
	
}
