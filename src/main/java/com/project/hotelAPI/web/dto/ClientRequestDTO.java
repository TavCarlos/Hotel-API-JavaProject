package com.project.hotelAPI.web.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

	@NotBlank
	@Size(min = 5,max = 100)
	private String name;
	@Size(min = 11 ,max = 11)
	@CPF
	private String cpf;
}
