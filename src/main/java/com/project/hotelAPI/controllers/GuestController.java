package com.project.hotelAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.services.GuestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	
	@PostMapping
	public ResponseEntity<Guest> createGuest(@Valid @RequestBody Guest guest) {
		Guest newGuest = guestService.createGuest(guest);
		return ResponseEntity.ok().body(newGuest);
	}

	
	public ResponseEntity<Guest> findGuestById(int id) {
		Guest guest = guestService.findGuestById(id);
		return ResponseEntity.ok().body(guest);
	}
	
	@GetMapping(path = "/findbypage/{page}")
	public Iterable<Guest> findAllGuestsByPage(@PathVariable int page) {
		return guestService.findAllGuestsByPage(page);
	}
	
	@GetMapping(path = "/findbyname/{searchName}")
	public ResponseEntity<Iterable<Guest>> findGuestsByNameContainingIgnoreCase(@PathVariable String searchName){
		List<Guest> guests = guestService.findGuestsByNameContainingIgnoreCase(searchName);
		return ResponseEntity.ok().body(guests);
	}
	
	@GetMapping(path = "/findbycpf/{cpf}")
	public ResponseEntity<Guest> findGuestByCpf(@PathVariable String cpf){
		Guest guest = guestService.findGuestByCpf(cpf);
		return ResponseEntity.ok().body(guest);
	}
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteGuestById(@RequestParam int guestId) {
		guestService.deleteGuestById(guestId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/delete/{guestId}/reservations")
	public ResponseEntity<String> deleteAllReservationsByGuestId(@PathVariable int guestId) {
		guestService.deleteAllReservationsByGuestId(guestId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/delete/{guestId}/reservations/{reservationId}")
	public ResponseEntity<String> deleteReservationByGuestId(@PathVariable int guestId, @PathVariable int reservationId) {
		guestService.deleteReservationByGuestId(guestId, reservationId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
