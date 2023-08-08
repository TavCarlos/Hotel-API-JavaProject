package com.project.hotelAPI.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.services.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/hotels")
public class HotelController {

	@Autowired
	HotelService hotelService;
	

	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {		
		Hotel newHotel = hotelService.createHotel(hotel);
		return ResponseEntity.ok().body(newHotel);
	}
	
	
	@GetMapping(path = "/{hotelId}")
	public ResponseEntity<Hotel> findHotelById(@PathVariable long hotelId){
		Hotel hotel =  hotelService.findHotelById(hotelId);
		return ResponseEntity.ok().body(hotel);
	}

	@GetMapping(path = "/page/{page}")
	public  ResponseEntity<Iterable<Hotel>> findAllHotelsByPage(@PathVariable int page) {
		Iterable<Hotel> hotels = hotelService.findAllHotelsByPage(page);
		return ResponseEntity.ok().body(hotels);
	}
	
	
	@GetMapping(path = "/search")
	public ResponseEntity<List<Hotel>> findHotelsByNameContainingIgnoreCase(@RequestParam(name = "name") String name){
		List<Hotel> hotels = hotelService.findHotelsByNameContainingIgnoreCase(name);
		return ResponseEntity.ok().body(hotels);
	}

	
	@PutMapping("/update")
	public ResponseEntity<Hotel> updateHotelNameById(@Valid @RequestBody Hotel hotel) {
		Hotel updatedHotel = hotelService.updateHotelNameById(hotel);
		return ResponseEntity.ok().body(updatedHotel);
	}

	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteHotelById(@PathVariable long id) {
		 hotelService.deleteHotelById(id);
		 return new ResponseEntity<String>(HttpStatus.NOT_FOUND);	
	}
}