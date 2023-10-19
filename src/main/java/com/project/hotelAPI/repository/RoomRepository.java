package com.project.hotelAPI.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.enums.StatusRoom;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	public Optional<Room> findByRoomNumber(int number);
	public Optional<Room> findFristByStatus(StatusRoom status);
	
	@Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT res.room.id FROM Reservation res WHERE :entry < res.checkOut AND :exit > res.checkIn)")
	public List<Room> findAvailableRoom(LocalDateTime entry, LocalDateTime exit);
}
