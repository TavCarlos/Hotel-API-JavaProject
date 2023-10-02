package com.project.hotelAPI.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.services.ClientService;
import com.project.hotelAPI.web.dto.ClientRequestDTO;
import com.project.hotelAPI.web.dto.ClientResponseDTO;
import com.project.hotelAPI.web.dto.mapper.ClientMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/client")
public class ClientController {

	private final ClientService clientService;
	
	
	@PostMapping("/create")
	public ResponseEntity<ClientResponseDTO> createGuest(@Valid @RequestBody ClientRequestDTO clientDto) {
		Client client = clientService.createClient(ClientMapper.toClientClass(clientDto));
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{cpf}")
				.buildAndExpand(client.getCpf()).toUri();
		
		return ResponseEntity.created(location).body(ClientMapper.toClientResponseDto(client));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<ClientResponseDTO> findGuestById(@PathVariable long id) {
		Client client = clientService.findClientById(id);
		return ResponseEntity.ok().body(ClientMapper.toClientResponseDto(client));
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClientResponseDTO> findGuestByCpf(@PathVariable @Valid String cpf){
		Client client = clientService.findClientByCpf(cpf);
		return ResponseEntity.ok().body(ClientMapper.toClientResponseDto(client));
	}
}
