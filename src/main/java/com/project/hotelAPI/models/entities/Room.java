package com.project.hotelAPI.models.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Room {

	@Id
	@Column(name = "roomNumber_id")
	private int roomNumber;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
	private List<Reservation> reservation;
	
	public Room() {

	}
	
	public Room(int roomNumber) {
		super();
		this.roomNumber = roomNumber;
	}


	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		if (this.reservation == null) {
			this.reservation = new ArrayList<>();
		}
		
		this.reservation.add(reservation);
	}
}