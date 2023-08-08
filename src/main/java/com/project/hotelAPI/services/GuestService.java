package com.project.hotelAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.hotelAPI.models.entities.Guest;
import com.project.hotelAPI.models.entities.Reservation;
import com.project.hotelAPI.models.repository.GuestRepository;
import com.project.hotelAPI.services.exceptions.EntityNotFoundException;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;
	
	
	public Guest createGuest(Guest guest) {
		guestRepository.save(guest);
		return guest;
	}

	
	public Guest findGuestById(long id) {
		return guestRepository.findById(id).orElseThrow(() 
				-> new EntityNotFoundException("Guest ID " + id + " not found" ));
	}
	
	
	public Iterable<Guest> findAllGuestsByPage(int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 10);
		return guestRepository.findAll(page);
	}
	
	
	public List<Guest> findGuestsByNameContainingIgnoreCase(String searchName){
		 List<Guest> guests = guestRepository.findByNameContainingIgnoreCase(searchName);
		 if(guests.isEmpty()) {
			 throw new EntityNotFoundException("Guest " + searchName + " not found" );
		 }
		 return guests;
	}
	
	
	public Guest findGuestByCpf(String Cpf){
		Guest guest = guestRepository.findByCpf(Cpf).orElseThrow(() 
				-> new EntityNotFoundException("Guest not found"));
		return guest;
	}
	
	
	public Guest updateGuest(Guest guest) {
		findGuestById(guest.getId());
		guestRepository.save(guest);
		return guest;
	}
	
	
	public void deleteGuestById(long guestId) {
		guestRepository.delete(findGuestById(guestId));
	}
	
	
	public void deleteAllReservationsByGuestId(long id) {
		Guest guest = findGuestById(id);
		List<Reservation> reservations = guest.getReservation();
		
		if(!reservations.isEmpty()) {
			reservations.forEach(reservation -> reservation.setCancelled(true));
		}
		
		guest.setReservation(null);
		guestRepository.save(guest);
	}
}
