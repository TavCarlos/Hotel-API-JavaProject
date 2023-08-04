package com.project.hotelAPI.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.HotelRepository;
import com.project.hotelAPI.models.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	public Room createRoom(int hotelId, int roomNumber) {
		Optional<Hotel> optinalReservation = hotelRepository.findById(hotelId);
		Hotel hotel = optinalReservation.get();
		
		Room newRoom = new Room(hotel, roomNumber);
		roomRepository.save(newRoom);
		return newRoom;
	}
	
	
	public Room findRoomById(int roomId) {
		 if (roomRepository.findById(roomId).isPresent()) {
			 Optional<Room> optionalRoom = roomRepository.findById(roomId);
			 return optionalRoom.get();
		 } else {
			 return null;
		 }
	}
	

	public Iterable<Room> findAllRooms(Pageable pageable){
		return roomRepository.findAll(pageable);
	}
	

	public List<Room> findAvailable(boolean available) {
		Iterable<Room> Rooms = roomRepository.findAll();
		return StreamSupport.stream(Rooms.spliterator(), false)
				.filter(room -> available ? room.getReservation().size() == 0 : room.getReservation().size() != 0)
				.collect(Collectors.toList());
	}
	
	
	public Room updateHotelName(int roomId, String newHotelName) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if(optionalRoom.isEmpty()) {
			throw new IllegalArgumentException("Room ID not found.");
		}
		
		Room room = optionalRoom.get();
		room.getHotel().setName(newHotelName);
		roomRepository.save(room);
		return room;
	}
	

	public Room updateRoomNumber(int roomId, int newRoomNumber) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if(optionalRoom.isEmpty()) {
			throw new IllegalArgumentException("Room ID not found.");
		}
		
		Room room = optionalRoom.get();
		room.setRoomNumber(newRoomNumber);
		roomRepository.save(room);
		return room;
	}
	
	public String deleteRoom(int roomId) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if(optionalRoom.isPresent()) {
			int roomName = optionalRoom.get().getRoomNumber();
			roomRepository.deleteById(roomId);
			return roomName + " Successfully deleted.";
		}
		return "Room ID not found.";
	}
	

	public String deleteRoomsByHotel(int hotelId) {
		Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
		
		if(!optionalHotel.isPresent()) {
			return "Hotel ID not found.";
		}
		
		Hotel hotel = optionalHotel.get();
		List<Room> rooms = roomRepository.findByHotel(hotel);
		
		for(Room room: rooms) {
			roomRepository.deleteById(room.getId());
		}
		return "All rooms related to " + hotel.getName() + " were successfully deleted";
	}
}
