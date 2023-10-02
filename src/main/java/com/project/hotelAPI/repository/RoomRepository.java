package com.project.hotelAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Long>, PagingAndSortingRepository<Room, Long>{

	public List<Room> findByRoomNumber(int pageNumber);
	
}
