package com.project.hotelAPI.models.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@NotBlank
	private Room room;
	
	@ManyToOne
	@NotBlank
	private Guest guest;
	
	@FutureOrPresent(message = "Invalid check-in date")
	private LocalDate checkInDate;
	
	@Future(message = "check-out must be in a future date")
	private LocalDate checkOutDate;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	public Reservation() {

	}
	
	public Reservation(LocalDate checkIn, LocalDate checkOut, Room room, Guest guest) {
		this.checkInDate = checkIn;
		this.checkOutDate = checkOut;
		this.room = room;
		this.guest = guest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoomId() {
		return room;
	}

	public void setRoomId(Room roomId) {
		this.room = roomId;
	}

	public Guest getGuestId() {
		return guest;
	}

	public void setGuestId(Guest guestId) {
		this.guest = guestId;
	}

	public LocalDate getCheckIn() {
		return checkInDate;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkInDate = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOutDate;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOutDate = checkOut;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}