package com.project.hotelAPI.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ReservationResponseDTO {

	private String bookingNumber;
	private LocalDateTime bookingDate;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private BigDecimal serviceCost;
	private BigDecimal cancelationFee;
	private String receipt;
	private String clientCpf;
	private int RoomRoomNumber;
}
