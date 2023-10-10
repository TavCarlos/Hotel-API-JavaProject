package com.project.hotelAPI.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hotelAPI.entity.Client;
import com.project.hotelAPI.repository.projections.ClientProjection;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query("SELECT c FROM Client c")
	Page<ClientProjection> findAllClients(Pageable pageable);
	
	public Optional<Client> findByCpf(String cpf);
	
}
