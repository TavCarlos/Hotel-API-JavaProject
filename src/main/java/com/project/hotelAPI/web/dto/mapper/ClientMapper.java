package com.project.hotelAPI.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.web.dto.ClientRequestDTO;
import com.project.hotelAPI.web.dto.ClientResponseDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

	public static Client toClientClass(ClientRequestDTO dto) {
		return new ModelMapper().map(dto, Client.class);
	}
	
	public static ClientResponseDTO toClientResponseDto(Client client) {
		return new ModelMapper().map(client, ClientResponseDTO.class);
	}
}
