package com.project.hotelAPI.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.repository.projections.ReservationProjection;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Page<ReservationProjection> findAllByClientCpf(String cpf ,Pageable pageable);
	
	Optional<Reservation> findByBookingNumber(String number);
	
	@Query("SELECT r FROM Reservation r WHERE :datein = r.checkIn AND :dateout = r.checkOut")
	List<Reservation> findBookingTimeConflicts(@Param("datein") LocalDateTime date, @Param("dateout") LocalDateTime dateOut);
	
	@Query("SELECT r FROM Reservation r WHERE (:datein > r.checkOut OR :dateout < r.checkIn) AND r.room NOT IN :rooms")
	List<Reservation> findRoomByReservationDates(@Param("datein") LocalDateTime date, @Param("dateout") LocalDateTime dateOut,
			@Param("rooms") List<Room> rooms);
}
