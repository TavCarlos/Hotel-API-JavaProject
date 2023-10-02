package com.project.hotelAPI.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.services.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/room")
public class RoomController {

	private final RoomService roomService;
	
	@PostMapping("/create")
	public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {	
		Room newRoom = roomService.createRoom(room);
		return ResponseEntity.ok().body(newRoom);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Room> findRoomById(@PathVariable long id) {
		Room room = roomService.findRoomById(id);
		return ResponseEntity.ok().body(room);
	}
}