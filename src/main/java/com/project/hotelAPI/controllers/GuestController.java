package com.project.hotelAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.models.repository.GuestRepository;

@RestController
@RequestMapping(path = "/guest")
public class GuestController {

	@Autowired
	private GuestRepository guestRepository;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT} )
	public Guest createGuest(@RequestParam String name, @RequestParam String cpf) {
		
		Guest newGuest = new Guest(name, cpf);
		guestRepository.save(newGuest);
		return newGuest;
	}
	
	@GetMapping(path = "/findbypage/{pageNumber}")
	public Iterable<Guest> getAllGuests(@PathVariable int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10);
		return guestRepository.findAll(page);
	}
	
	@GetMapping(path = "/findbyname/{searchName}")
	public Iterable<Guest> getGuestByName(@PathVariable String searchName){
		return guestRepository.findByNameContainingIgnoreCase(searchName);
	}
	
	@GetMapping(path = "/findbycpf/{searchCpf}")
	public Iterable<Guest> getGuestByCpf(@PathVariable String searchCpf){
		return guestRepository.findByCpf(searchCpf);
	}
	
	@DeleteMapping(path = "/deleteguest/{id}")
	public String deleteGuestCpf(@PathVariable String id) {
		
		
		if(guestRepository.existsById(id)) {
			guestRepository.deleteById(id);
			return "Guest sucessifully deleted";
		}
		return "Guest not found";
	}
}
