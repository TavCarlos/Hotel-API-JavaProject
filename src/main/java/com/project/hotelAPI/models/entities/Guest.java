package com.project.hotelAPI.models.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String cpf;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Reservation> reservation;
	
	public Guest() {

	}
	
	
	public Guest(String nome, String cpf, List<Reservation> reservation) {
		super();
		this.name = nome;
		this.cpf = cpf;
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
}
