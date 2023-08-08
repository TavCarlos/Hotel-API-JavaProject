package com.project.hotelAPI.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.hotelAPI.models.entities.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long>, PagingAndSortingRepository<Hotel, Long>{

	public List<Hotel> findByNameContainingIgnoreCase (String name);
}
