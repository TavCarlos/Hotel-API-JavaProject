package com.project.hotelAPI.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.project.hotelAPI.entity.Room;
import com.project.hotelAPI.web.dto.RoomRequestDTO;
import com.project.hotelAPI.web.dto.RoomResponseDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomMapper {

	public static Room toRoomClass(RoomRequestDTO roomDto) {
		return new ModelMapper().map(roomDto, Room.class);
	}
	
	public static RoomResponseDTO toDto(Room room) {
		return new ModelMapper().map(room, RoomResponseDTO.class);
	}
}
