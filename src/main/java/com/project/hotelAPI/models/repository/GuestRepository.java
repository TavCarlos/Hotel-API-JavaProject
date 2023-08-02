package com.project.hotelAPI.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Guest;

public interface GuestRepository extends CrudRepository<Guest, Integer>, PagingAndSortingRepository<Guest, Integer>{

	public Iterable<Guest> findByNameContainingIgnoreCase(String name);
	public Optional<Guest> findByCpf(String cpf);
}
