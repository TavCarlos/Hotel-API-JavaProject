package com.project.hotelAPI.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Room {

	@Id
	@Column(name = "id")
	private int roomNumber;
	
	@OneToMany
	private List<Guest> guest;
	
	public Room() {

	}
	
	public Room(int roomNumber, List<Guest> guest) {
		super();
		this.roomNumber = roomNumber;
		this.guest = guest;
	}


	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public List<Guest> getGuest() {
		return guest;
	}

	public void setGuest(List<Guest> guest) {
		this.guest = guest;
	}
}
