package com.project.hotelAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Reservation;
import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.enums.StatusRoom;
import com.project.hotelAPI.exceptions.EntityNotFoundException;
import com.project.hotelAPI.exceptions.RoomUniqueViolationException;
import com.project.hotelAPI.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	
	@Transactional
	public Room createRoom(Room room) {
		try {
			return roomRepository.save(room);
		} catch(DataIntegrityViolationException ex) {
			throw new RoomUniqueViolationException(String.format("Room number '%s' already exists", room.getRoomNumber()));
		}
	}
	
	@Transactional(readOnly = true)
	public Room findRoomById(long id) {
		 return roomRepository.findById(id).orElseThrow(()
				 -> new EntityNotFoundException(String.format("room id '%is' not found", id)));
	}
	
	@Transactional(readOnly = true)
	public Room findByRoomNumber(int number) {
		return roomRepository.findByRoomNumber(number).orElseThrow(
				() -> new EntityNotFoundException(String.format("Room '%s' not found", number)));
	}
	
	@Transactional(readOnly = true)
	public Room findFreeRoom(Reservation reservation) {
		Optional<Room> room = roomRepository.findFristByStatus(StatusRoom.FREE);
		
		if(!room.isEmpty()) {
			return room.get();
		}
		
		List<Room> rooms = roomRepository.findAvailableRoom(reservation.getCheckIn(), reservation.getCheckOut());
		
		if(rooms.isEmpty()) {
			throw new EntityNotFoundException("There's no available room");
		}
		
		return rooms.get(0);
	}
	
	
}
