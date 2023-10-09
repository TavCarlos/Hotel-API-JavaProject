package com.project.hotelAPI.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.web.dto.ReservationRequestDTO;
import com.project.hotelAPI.web.dto.ReservationResponseDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationMapper {

	public static Reservation toReservationClass(ReservationRequestDTO dto) {
		return new ModelMapper().map(dto, Reservation.class);
	}
	
	public static ReservationResponseDTO toDto(Reservation reservation) {
		return new ModelMapper().map(reservation, ReservationResponseDTO.class);
	}
}
