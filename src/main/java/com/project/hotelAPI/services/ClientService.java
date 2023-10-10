package com.project.hotelAPI.services;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.exceptions.CpfUniqueViolationException;
import com.project.hotelAPI.exceptions.EntityNotFoundException;
import com.project.hotelAPI.repository.ClientRepository;
import com.project.hotelAPI.repository.projections.ClientProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	
	@Transactional
	public Client createClient(Client client) {
		try {
			return clientRepository.save(client);			
		} catch(DataIntegrityViolationException ex) {
			throw new CpfUniqueViolationException(String.format("CPF '%s' already registered", client.getCpf()));
		}
	}

	@Transactional(readOnly = true)
	public Client findClientById(long id) {
		return clientRepository.findById(id).orElseThrow(() 
				-> new EntityNotFoundException(String.format("Guest '$s' not found", id)));
	}
	
	@Transactional(readOnly = true)
	public Client findClientByCpf(String cpf){
		return clientRepository.findByCpf(cpf).orElseThrow(() 
				-> new EntityNotFoundException(String.format("Guest '$s' not found", cpf)));
	}
	
	@Transactional(readOnly = true)
	public Page<ClientProjection> findAllClients(Pageable pageable) {
		return clientRepository.findAllClients(pageable);
	}
}
