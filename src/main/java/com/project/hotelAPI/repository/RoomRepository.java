package com.project.hotelAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.enums.StatusRoom;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	public Optional<Room> findByRoomNumber(int number);
	public Optional<Room> findFristByStatus(StatusRoom status);
}
