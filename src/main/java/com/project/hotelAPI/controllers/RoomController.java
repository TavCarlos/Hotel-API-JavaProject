package com.project.hotelAPI.controllers;

import java.util.List;

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

import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.services.RoomService;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@PostMapping
	public Room createRoom(@RequestParam int hotelId, 
						@RequestParam int roomNumber) {	
		return roomService.createRoom(hotelId, roomNumber);
	}
	
	
	@GetMapping(path = "/search/{roomId}")
	public Room findRoomById(@PathVariable int roomId) {
		return roomService.findRoomById(roomId);
	}
	
	@GetMapping(path = "/{page}")
	public Iterable<Room> findAllRooms(@PathVariable int page){
		Pageable pageable = PageRequest.of(page, 10);
		return roomService.findAllRooms(pageable);
	}
	
	@GetMapping(path = "/available")
	public List<Room> findAvailable(@RequestParam(required = true, defaultValue = "true") boolean available) {
		return roomService.findAvailable(available);
	}
	
	
	@PutMapping(path = "/updateroom")
	public Room updateHotelName(@RequestParam int roomId, 
								@RequestParam  String newHotelName) {
	return roomService.updateHotelName(roomId, newHotelName);
	}
	
	@PutMapping(path = "/updateroomNumber")
	public Room updateRoomNumber(@RequestParam int roomId, 
								@RequestParam int newRoomNumber) {
		return roomService.updateRoomNumber(roomId, newRoomNumber);
	}
	
	@DeleteMapping(path = "/delete")
	public String deleteRoom(@RequestParam int roomId) {
		return roomService.deleteRoom(roomId);
	}
	
	@DeleteMapping("/deleteall")
	public String deleteRoomsByHotel(@RequestParam int hotelId) {
		return roomService.deleteRoomsByHotel(hotelId);
	}
}