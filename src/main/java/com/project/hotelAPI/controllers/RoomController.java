package com.project.hotelAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.RoomRepository;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

	@Autowired
	RoomRepository roomRepository;
	
	@PostMapping
//	public Room createRoom(@RequestParam int roomNumber) {
//		Room newRoom = new Room(roomNumber);
//		roomRepository.save(newRoom);
//		return newRoom;
//	}
	
	@GetMapping(path = "/search/{roomNumber}")
	public Iterable<Room> findRoomById(@PathVariable int roomNumber) {
		return roomRepository.findByRoomNumber(roomNumber);
	}
	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Room> findAllRooms(@PathVariable int pageNumber){
		Pageable page = PageRequest.of(pageNumber, 10);
		return roomRepository.findAll(page);
	}
	
	@DeleteMapping(path = "/{roomNumber}")
	public String deleteRoom(@PathVariable int roomNumber) {
		
		if(roomRepository.existsById(roomNumber)) {
			roomRepository.deleteById(roomNumber);
			return "Room sucessfully deleted.";
		}
		return "Room number not found.";
	}
}
