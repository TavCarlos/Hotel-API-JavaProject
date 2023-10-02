package com.project.hotelAPI.entity;

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
	private long id;
	
	@ManyToOne
	@NotBlank
	private Room room;
	
	@ManyToOne
	@NotBlank
	private Client guest;
	
	@FutureOrPresent(message = "Invalid check-in date")
	private LocalDate checkInDate;
	
	@Future(message = "check-out must be in a future date")
	private LocalDate checkOutDate;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isCancelled;
	
	public Reservation() {

	}
	
	public Reservation(LocalDate checkIn, LocalDate checkOut, Room room, Client guest) {
		this.checkInDate = checkIn;
		this.checkOutDate = checkOut;
		this.room = room;
		this.guest = guest;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Room getRoomId() {
		return room;
	}

	public void setRoomId(Room roomId) {
		this.room = roomId;
	}

	public Client getGuestId() {
		return guest;
	}

	public void setGuestId(Client guestId) {
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

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
}