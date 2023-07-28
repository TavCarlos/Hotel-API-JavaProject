package com.project.hotelAPI.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Room;

public interface RoomRepository extends CrudRepository<Room, Integer>, PagingAndSortingRepository<Room, Integer>{

	public Iterable<Room> findByRoomNumber(int pageNumber);
}
