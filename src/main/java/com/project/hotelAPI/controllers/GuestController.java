package com.project.hotelAPI.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.models.repository.GuestRepository;
import com.project.hotelAPI.models.repository.ReservationRepository;

@RestController
@RequestMapping(path = "/guest")
public class GuestController {

	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT} )
	public Guest createGuest(@RequestParam String name, @RequestParam String cpf, @RequestParam int reservationId) {
		
		Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(optionalReservation.get());
		
		Guest newGuest = new Guest(name, cpf, reservations);
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
	public String deleteGuestCpf(@PathVariable int id) {
		
		if(guestRepository.existsById(id)) {
			guestRepository.deleteById(id);
			return "Guest sucessifully deleted";
		}
		return "Guest not found";
	}
}
