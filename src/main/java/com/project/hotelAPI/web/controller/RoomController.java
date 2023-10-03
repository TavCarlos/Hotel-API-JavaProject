package com.project.hotelAPI.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.services.RoomService;
import com.project.hotelAPI.web.dto.RoomRequestDTO;
import com.project.hotelAPI.web.dto.RoomResponseDTO;
import com.project.hotelAPI.web.dto.mapper.RoomMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/room")
public class RoomController {

	private final RoomService roomService;
	
	@PostMapping("/create")
	public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody RoomRequestDTO roomDto) {	
		Room room = roomService.createRoom(RoomMapper.toRoomClass(roomDto));
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(room.getRoomNumber()).toUri();
		
		return ResponseEntity.created(location).body(RoomMapper.toDto(room));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<RoomResponseDTO> findRoomById(@PathVariable long id) {
		Room room = roomService.findRoomById(id);
		return ResponseEntity.ok().body(RoomMapper.toDto(room));
	}
	
	@GetMapping("{value}")
	public ResponseEntity<RoomResponseDTO> findRoomByNumber(@PathVariable int value){
		Room room = roomService.findByRoomNumber(value);
		return ResponseEntity.ok().body(RoomMapper.toDto(room));
	}
}