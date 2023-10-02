package com.project.hotelAPI.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.repository.ClientRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository ClientRepository;
	
	@Transactional
	public Client createClient(Client client) {
		return ClientRepository.save(client);
	}

	@Transactional(readOnly = true)
	public Client findClientById(long id) {
		return ClientRepository.findById(id).orElseThrow(() 
				-> new EntityNotFoundException(String.format("Guest '$s' not found", id)));
	}
	
	@Transactional(readOnly = true)
	public Client findClientByCpf(String cpf){
		return ClientRepository.findByCpf(cpf).orElseThrow(() 
				-> new EntityNotFoundException(String.format("Guest '$s' not found", cpf)));
	}
}
