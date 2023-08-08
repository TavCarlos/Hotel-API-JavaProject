package com.project.hotelAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {	
		Room newRoom = roomService.createRoom(room);
		return ResponseEntity.ok().body(newRoom);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Room> findRoomById(@PathVariable long id) {
		Room room = roomService.findRoomById(id);
		return ResponseEntity.ok().body(room);
	}
	
	@GetMapping(path = "/all/{page}")
	public ResponseEntity<Iterable<Room>> findAllRooms(@PathVariable int page){
		Iterable<Room> rooms = roomService.findAllRooms(page);
		return ResponseEntity.ok().body(rooms);
	}
	
	
	@PutMapping(path = "/update")
	public ResponseEntity<Room> updateHotelName(@Valid @RequestBody Room room) {
		Room updatedRoom = roomService.updateHotelName(room);
		return ResponseEntity.ok().body(updatedRoom);
	}
	
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable long id) {
		roomService.deleteRoom(id);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/delete/all/{hotelId}")
	public ResponseEntity<String> deleteRoomsByHotel(@PathVariable long hotelId) {
		roomService.deleteRoomsByHotel(hotelId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}