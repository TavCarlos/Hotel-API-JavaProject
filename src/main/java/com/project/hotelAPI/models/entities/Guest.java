package com.project.hotelAPI.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Guest {

	@Id
	@JoinColumn(name = "cpf_id")
	private String cpf;
	
	private String name;
	
	@OneToOne(mappedBy = "guest")
	private Reservation reservation;
	
	public Guest() {

	}
	
	
	public Guest(String nome, String cpf) {
		super();
		this.name = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
