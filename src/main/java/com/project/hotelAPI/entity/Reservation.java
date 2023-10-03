package com.project.hotelAPI.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "reservation_number", nullable = false, unique = true)
	private int reservationNumber;
	@Column(name = "booking_date", nullable = false)
	private LocalDateTime bookingDate;
	@Column(name = "check_in_date")
	private LocalDateTime checkInDate;
	@Column(name = "check_out_date")
	private LocalDateTime checkOutDate;
	@Column(name = "value", columnDefinition = "decimal(7,2)")
	private BigDecimal value;
	@Column(name = "cancelation_fee", columnDefinition = "decimal(7,2)")
	private BigDecimal cancelationFee;
	@ManyToOne
	@Column(name = "client_id", nullable = false)
	private Client client;
	@ManyToOne
	@Column(name = "room_id", nullable = false)
	private Room room;
}