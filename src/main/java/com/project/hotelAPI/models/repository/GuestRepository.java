package com.project.hotelAPI.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Guest;

public interface GuestRepository extends CrudRepository<Guest, Integer>, PagingAndSortingRepository<Guest, Integer>{

	public List<Guest> findByNameContainingIgnoreCase(String name);
	public Optional<Guest> findByCpf(String cpf);
	
}
