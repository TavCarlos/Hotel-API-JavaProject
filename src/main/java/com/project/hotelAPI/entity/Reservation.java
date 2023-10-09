package com.project.hotelAPI.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "booking_number", nullable = false, unique = true)
	private String bookingNumber;
	@Column(name = "booking_date", nullable = false)
	private LocalDateTime bookingDate;
	@Column(name = "check_in_date", nullable = false)
	private LocalDateTime checkIn;
	@Column(name = "check_out_date", nullable = false)
	private LocalDateTime CheckOut;
	@Column(name = "serviceCost", columnDefinition = "decimal(7,2)")
	private BigDecimal serviceCost;
	@Column(name = "cancellation_fee", columnDefinition = "decimal(7,2)")
	private BigDecimal cancelationFee;
	@Column(name = "receipt")
	private String receipt;
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
}