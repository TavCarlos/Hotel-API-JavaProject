package com.project.hotelAPI.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.enums.StatusRoom;
import com.project.hotelAPI.repository.RoomRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	
	@Transactional
	public Room createRoom(Room room) {
		return roomRepository.save(room);
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
	
	public Room findFreeRoom() {
		return roomRepository.findFristByStatus(StatusRoom.FREE).orElseThrow(
				() -> new EntityNotFoundException("There's no free room available"));
	}
	
}
