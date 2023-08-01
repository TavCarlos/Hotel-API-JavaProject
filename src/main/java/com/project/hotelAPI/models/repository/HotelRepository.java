package com.project.hotelAPI.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Integer>, PagingAndSortingRepository<Hotel, Integer>{

	public Iterable<Hotel> findByNameContainingIgnoreCase (String name);
}
