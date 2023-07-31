package com.project.hotelAPI.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.models.entities.Room;
import com.project.hotelAPI.models.repository.HotelRepository;
import com.project.hotelAPI.models.repository.RoomRepository;

@RestController
@RequestMapping(path = "/hotel")
public class HotelController {

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@PostMapping
	public Hotel createHotel(@RequestParam String hotelName, @RequestParam int room_id) {
		
		Optional<Room> optionalRoom = roomRepository.findById(room_id);
		Hotel hotel = new Hotel(hotelName);
		hotel.setRoom(optionalRoom.get());
		
		hotelRepository.save(hotel);
		return hotel;
	}
	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Hotel> findHotel(@PathVariable int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10);
		return hotelRepository.findAll(page);
	}
}
