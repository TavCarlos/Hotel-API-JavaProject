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

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.services.GuestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/guests")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	
	@PostMapping(path = "/create")
	public ResponseEntity<Guest> createGuest(@Valid @RequestBody Guest guest) {
		Guest newGuest = guestService.createGuest(guest);
		return ResponseEntity.ok().body(newGuest);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Guest> findGuestById(@PathVariable long id) {
		Guest guest = guestService.findGuestById(id);
		return ResponseEntity.ok().body(guest);
	}
	
	@GetMapping(path = "/page/{page}")
	public Iterable<Guest> findAllGuestsByPage(@PathVariable int page) {
		return guestService.findAllGuestsByPage(page);
	}
	
	@GetMapping(path = "/search")
	public ResponseEntity<Iterable<Guest>> findGuestsByNameContainingIgnoreCase(@RequestParam(name = "name") String name){
		List<Guest> guests = guestService.findGuestsByNameContainingIgnoreCase(name);
		return ResponseEntity.ok().body(guests);
	}
	
	@GetMapping(path = "/cpfSearch")
	public ResponseEntity<Guest> findGuestByCpf(@RequestParam(name = "cpf") String cpf){
		Guest guest = guestService.findGuestByCpf(cpf);
		return ResponseEntity.ok().body(guest);
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<Guest> updateGuest(@Valid @RequestBody Guest guest){
		Guest updatedGuest = guestService.updateGuest(guest);
		return ResponseEntity.ok().body(updatedGuest);
	}
	
	
	@DeleteMapping(path = "/{GuestId}")
	public ResponseEntity<String> deleteGuestById(@PathVariable long guestId) {
		guestService.deleteGuestById(guestId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "{guestId}/reservations")
	public ResponseEntity<String> deleteAllReservationsByGuestId(@PathVariable long guestId) {
		guestService.deleteAllReservationsByGuestId(guestId);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
