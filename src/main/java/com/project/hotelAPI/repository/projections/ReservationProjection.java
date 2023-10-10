package com.project.hotelAPI.repository.projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public interface ReservationProjection {

	String getBookingNumber();
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	LocalDateTime getBookingDate();
	@JsonFormat(pattern = "yyy-MM-dd hh:mm:ss")
	LocalDateTime getCheckIn();
	@JsonFormat(pattern = "yyy-MM-dd hh:mm:ss")
	LocalDateTime getCheckOut();
	BigDecimal getServiceCost();
	BigDecimal getCancelationFee();
	String getReceipt();
	String getClientCpf();
	int getRoomRoomNumber();
}
