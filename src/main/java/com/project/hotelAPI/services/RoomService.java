package com.project.hotelAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.RoomRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HotelService hotelService;
	
	
	public Room createRoom(Room room) {
		roomRepository.save(room);
		return room;
	}
	
	
	public Room findRoomById(long id) {
		 return roomRepository.findById(id).orElseThrow(()
				 -> new EntityNotFoundException("Room Id " + id + " not found"));
	}
	
	public Iterable<Room> findAllRooms(int page){
		Pageable pageable = PageRequest.of(page, 10);
		return roomRepository.findAll(pageable);
	}
	
	public Room updateHotelName(Room room) {
		findRoomById(room.getId());
		roomRepository.save(room);
		return room;
	}
	
	public void deleteRoom(long id) {
		roomRepository.delete(findRoomById(id));
	}
	
	
	public void deleteRoomsByHotel(long hotelId) {
		Hotel hotel = hotelService.findHotelById(hotelId);
		List<Room> rooms = roomRepository.findByHotel(hotel);
		
		for(Room room: rooms) {
			deleteRoom(room.getId());
		}
	}
	
}
