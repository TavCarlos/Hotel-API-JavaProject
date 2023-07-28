package com.project.hotelAPI.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Room {

	@Id
	@Column(name = "id")
	private int roomNumber;
	
	@OneToOne
	private Guest guest;
	
	public Room() {

	}
	
	public Room(int roomNumber, Guest guest) {
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


	public Guest getReservation() {
		return guest;
	}

	public void setReservation(Guest guest) {
		this.guest = guest;
	}

}
