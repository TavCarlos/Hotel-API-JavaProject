package com.project.hotelAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.models.repository.HotelRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

@Service
public class HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	public Hotel createHotel(Hotel hotel) {		
		hotelRepository.save(hotel);
		return hotel;
	}
	
	public Hotel findHotelById(int hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(
				() -> new EntityNotFoundException("Hotel ID " + hotelId + " not found."));
	}
	
	
	public Iterable<Hotel> findAllHotelsByPage(int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10);
		return hotelRepository.findAll(page);
	}
	
	
	public List<Hotel> findHotelsByNameContainingIgnoreCase(String name){
		List<Hotel> hotels = hotelRepository.findByNameContainingIgnoreCase(name);
		if (hotels.isEmpty()) {
			throw new EntityNotFoundException("Hotel not found");
		}
		return hotels;
	}
	
	public Hotel updateHotelNameById(Hotel hotel) {
		findHotelById(hotel.getId());
		hotelRepository.save(hotel);
		return hotel;
	}
	
	public void deleteHotelById(int id) {
		hotelRepository.delete(findHotelById(id));
	}
}
