package com.project.hotelAPI.web.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {

	@CPF
	@Size(min = 11, max = 11)
	private String clientCpf;
	@FutureOrPresent
	private LocalDateTime checkIn;
	@FutureOrPresent
	private LocalDateTime checkOut;
}
