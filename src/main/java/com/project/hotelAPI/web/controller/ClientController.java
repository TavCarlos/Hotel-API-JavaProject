package com.project.hotelAPI.web.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.repository.projections.ClientProjection;
import com.project.hotelAPI.services.ClientService;
import com.project.hotelAPI.web.dto.ClientRequestDTO;
import com.project.hotelAPI.web.dto.ClientResponseDTO;
import com.project.hotelAPI.web.dto.PageableDTO;
import com.project.hotelAPI.web.dto.mapper.ClientMapper;
import com.project.hotelAPI.web.dto.mapper.PageableMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/client")
public class ClientController {

	private final ClientService clientService;
	
	
	@PostMapping("/create")
	public ResponseEntity<ClientResponseDTO> createClients(@Valid @RequestBody ClientRequestDTO clientDto) {
		Client client = clientService.createClient(ClientMapper.toClientClass(clientDto));
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{cpf}")
				.buildAndExpand(client.getCpf()).toUri();
		
		return ResponseEntity.created(location).body(ClientMapper.toClientResponseDto(client));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<ClientResponseDTO> findClientsById(@PathVariable long id) {
		Client client = clientService.findClientById(id);
		return ResponseEntity.ok().body(ClientMapper.toClientResponseDto(client));
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClientResponseDTO> findClientsByCpf(@PathVariable @Valid String cpf){
		Client client = clientService.findClientByCpf(cpf);
		return ResponseEntity.ok().body(ClientMapper.toClientResponseDto(client));
	}
	
	@GetMapping
	public ResponseEntity<PageableDTO> findAllClients(@PageableDefault Pageable pageable){
		Page<ClientProjection> clients = clientService.findAllClients(pageable);
		return ResponseEntity.ok().body(PageableMapper.toDto(clients));
	}
}
