package com.project.hotelAPI.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Hotel;
import com.project.hotelAPI.models.entities.Room;

public interface RoomRepository extends CrudRepository<Room, Long>, PagingAndSortingRepository<Room, Long>{

	public List<Room> findByRoomNumber(int pageNumber);
	
	public List<Room> findByHotel(Hotel hotel);
}
