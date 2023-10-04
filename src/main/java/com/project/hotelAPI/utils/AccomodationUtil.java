package com.project.hotelAPI.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccomodationUtil {

	private static String ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private static String NUMBERS = "0123456789";
	private static String BOOKING_NUMBER = "";
	
	
	public static String generateBookingNumber() {
		LocalDateTime date = LocalDateTime.now();
		BOOKING_NUMBER += date.toString().substring(0, 11)
				.replace("-", "").replace("T", "-");		
		
		generateRandomchar(ALPHABET);
		generateRandomchar(NUMBERS);
		
		return BOOKING_NUMBER;
	}
	
	private static void generateRandomchar(String randomChar) {
		SecureRandom random = new SecureRandom();
		
		for(int i = 0; i <= 2 ; i++) {
			int index = random.nextInt(randomChar.length());
			BOOKING_NUMBER += randomChar.charAt(index);
		}
	}
	
}
