package com.project.hotelAPI.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.models.entities.Reservation;
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
	public Iterable<Guest> findAllGuestsByPage(@PathVariable int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10);
		return guestRepository.findAll(page);
	}
	
	@GetMapping(path = "/findbyname/{searchName}")
	public Iterable<Guest> findGuestsByNameContainingIgnoreCase(@PathVariable String searchName){
		return guestRepository.findByNameContainingIgnoreCase(searchName);
	}
	
	@GetMapping(path = "/findbycpf/{searchCpf}")
	public Guest findGuestByCpf(@PathVariable String searchCpf){
		Optional<Guest> optionalguest = guestRepository.findByCpf(searchCpf);
		Guest guest = optionalguest.orElseThrow(() -> new IllegalArgumentException("Guest CPF not found."));
		return guest;
	}
	
	@PutMapping(path = "/teste")
	public Guest updateReservationByGuestId(@RequestParam int guestId, 
										@RequestParam int reservationId, 
										@RequestParam String newCheckIn, 
										@RequestParam String newCheckOut){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate CheckIn = LocalDate.parse(newCheckIn, formatter);
		LocalDate CheckOut = LocalDate.parse(newCheckOut, formatter);
		
		Optional<Guest> optionalguest = guestRepository.findById(guestId);
		Guest guest = optionalguest.orElseThrow(() -> new IllegalArgumentException("Guest ID not found."));
		
		for(Reservation reservation: guest.getReservation()) {
			if(reservation.getId() == reservationId) {
				reservation.setCheckIn(CheckIn);
				reservation.setCheckOut(CheckOut);
			}
		}
		
		guestRepository.save(guest);
		return guest;
	}
	
	@DeleteMapping(path = "/delete")
	public String deleteGuestById(@RequestParam int guestId) {
		Optional<Guest> optionalGuest = guestRepository.findById(guestId);
		if(!optionalGuest.isPresent()) {
			return "Guest ID not found.";
		}
		
		guestRepository.delete(optionalGuest.get());
		return "Guest successfully deleted.";
	}
	
	@DeleteMapping(path = "/delete/{guestId}/reservations")
	public String deleteAllReservationsByGuestId(@PathVariable int guestId) {
		Optional<Guest> optionalGuest = guestRepository.findById(guestId);
		if(!optionalGuest.isPresent()) {
			return "Guest ID not found.";
		}
		
		Guest guest = optionalGuest.get();
		List<Reservation> reservations = guest.getReservation();
		
		if(!reservations.isEmpty()) {
			reservations.forEach(reservation -> reservation.setDeleted(true));
		}
		
		guest.setReservation(null);
		guestRepository.save(guest);
		return "All reservations of the guest" + guest.getName() + "have been deleted";
	}
	
	@DeleteMapping(path = "/delete/{guestId}/reservations/{reservationId}")
	public String deleteReservationByGuestId(@PathVariable int guestId, @PathVariable int reservationId) {
		Optional<Guest> optionalGuest = guestRepository.findById(guestId);
		if(optionalGuest.isEmpty()) {
			return "Guest not found.";
		}
		
		Guest guest = optionalGuest.get();
		guest.getReservation().stream()
		.filter(reservation -> reservation.getId() == reservationId)
		.forEach(reservation -> reservation.setDeleted(true));
		
		guestRepository.save(guest);
		
		return "Reservation successfully deleted.";
	}
}
