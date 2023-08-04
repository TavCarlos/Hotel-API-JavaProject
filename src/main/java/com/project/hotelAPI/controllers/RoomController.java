package com.project.hotelAPI.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.HotelRepository;
import com.project.hotelAPI.models.repository.RoomRepository;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@PostMapping
	public Room createRoom(@RequestParam int hotelId, @RequestParam int roomNumber) {
		
		Optional<Hotel> optinalReservation = hotelRepository.findById(hotelId);
		Hotel hotel = optinalReservation.get();
		
		Room newRoom = new Room(hotel, roomNumber);
		roomRepository.save(newRoom);
		
		return newRoom;
	}
	
	
	@GetMapping(path = "/search/{roomId}")
	public Room findRoomById(@PathVariable int roomId) {
		 if (roomRepository.findById(roomId).isPresent()) {
			 Optional<Room> optionalRoom = roomRepository.findById(roomId);
			 return optionalRoom.get();
		 } else {
			 return null;
		 }
	}
	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Room> findAllRooms(@PathVariable int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return roomRepository.findAll(page);
	}
	
	@GetMapping(path = "/available")
	public List<Room> findAvailable(@RequestParam(required = true, defaultValue = "true") boolean available) {
		Iterable<Room> allRooms = roomRepository.findAll();
		return StreamSupport.stream(allRooms.spliterator(), false)
				.filter(room -> available ? room.getReservation().size() == 0 : room.getReservation().size() != 0)
				.collect(Collectors.toList());
	}
	
	
	@PutMapping(path = "/updateroom")
	public Room updateHotelNameByRoom(@RequestParam int roomId, 
									@RequestParam  String newHotelName) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if(optionalRoom.isEmpty()) {
			throw new IllegalArgumentException("Room ID not found.");
		}
		
		Room room = optionalRoom.get();
		room.getHotel().setName(newHotelName);
		roomRepository.save(room);
		return room;
	}
	
	@PutMapping(path = "/updateroomNumber")
	public Room UpdateRoomNumber(@RequestParam int roomId, @RequestParam int newRoomNumber) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if(optionalRoom.isEmpty()) {
			throw new IllegalArgumentException("Room ID not found.");
		}
		
		Room room = optionalRoom.get();
		room.setRoomNumber(newRoomNumber);
		roomRepository.save(room);
		return room;
	}
	
	
	@DeleteMapping(path = "/delete")
	public String deleteRoom(@RequestParam int roomId) {
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if(optionalRoom.isPresent()) {
			int roomName = optionalRoom.get().getRoomNumber();
			roomRepository.deleteById(roomId);
			return roomName + " Successfully deleted.";
		}
		return "Room ID not found.";
	}
	
	@DeleteMapping("/deleteall")
	public String deleteRoomsByHotel(@RequestParam int hotelId) {
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
