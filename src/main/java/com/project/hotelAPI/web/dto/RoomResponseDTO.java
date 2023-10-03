package com.project.hotelAPI.web.dto;

import com.project.hotelAPI.enums.StatusRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {

	private long id;
	private int roomNumber;
	private StatusRoom status;
}
