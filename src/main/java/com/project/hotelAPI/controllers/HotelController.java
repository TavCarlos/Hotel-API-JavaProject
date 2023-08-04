package com.project.hotelAPI.controllers;

import java.util.Optional;

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
import com.project.hotelAPI.models.repository.HotelRepository;

@RestController
@RequestMapping(path = "/hotel")
public class HotelController {

	@Autowired
	HotelRepository hotelRepository;
	
	@PostMapping
	public Hotel createHotel(@RequestParam String hotelName) {
		Hotel hotel = new Hotel(hotelName);
		
		hotelRepository.save(hotel);
		return hotel;
	}
	
	@GetMapping(path = "/searchid/{hotelId}")
	public Hotel findHotelById(@PathVariable int hotelId){
		if (hotelRepository.findById(hotelId).isPresent()) {
			Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
			Hotel hotel = optionalHotel.get();
			return hotel;
		} else {
			return null;			
		}
	}
	
	@GetMapping(path = "/{pageNumber}")
	public Iterable<Hotel> findHotel(@PathVariable int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10);
		return hotelRepository.findAll(page);
	}
	
	
	@GetMapping(path = "/search/{name}")
	public Iterable<Hotel> findHotelByName(@PathVariable String name){
		return hotelRepository.findByNameContainingIgnoreCase(name);
	}
	
	
	@PutMapping("/updatehotel")
	public Hotel updateHotlNameById(@RequestParam int hotelId, 
									@RequestParam String newHotelName) {
		Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
		if(optionalHotel.isEmpty()) {
			throw new IllegalArgumentException("Hotel ID not found");
		}
		
		Hotel hotel = optionalHotel.get();
		hotel.setName(newHotelName);
		hotelRepository.save(hotel);
		return hotel;
	}
	
	
	
	
	@DeleteMapping(path = "/delete")
	public String deleterHotelById(@RequestParam int hotelId) {
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		if(hotel.isPresent()) {
			String hotelName = hotel.get().getName();
			hotelRepository.deleteById(hotelId);
			return hotelName + " Successfully deleted.";
		}
		return "Hotel ID not found.";
	}
}