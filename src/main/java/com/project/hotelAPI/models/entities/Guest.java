package com.project.hotelAPI.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(min = 9, max = 14)
	private String cpf;
	
	@NotBlank
	private String name;
	
	@OneToMany(mappedBy = "guest")
	private List<Reservation> reservations;
	
	public Guest() {

	}
	
	public Guest(String nome, String cpf) {
		super();
		this.name = nome;
		this.cpf = cpf;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public List<Reservation> getReservation() {
		return reservations;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservations = reservation;
	}
}
