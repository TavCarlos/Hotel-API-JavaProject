package com.project.hotelAPI.models.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany
	private List<Room> rooms;
	
	public Hotel() {

	}
	
	public Hotel(String name, List<Room> room) {
		super();
		this.name = name;
		this.rooms = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Room> getRoom() {
		return rooms;
	}

	public void setRoom(List<Room> rooms) {
		this.rooms = rooms;
	}
}
