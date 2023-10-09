package com.project.hotelAPI.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccomodationUtil {
	
	private static String ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private static String NUMBERS = "0123456789";
	private static double dailyCost = 20.00;
	private static double THIRTY_PERCENT = 0.3;
	private static double SEVENTY_PERCENT = 0.7;
	private static double NINETY_PERCENT = 0.9;
	
	public static String generateBookingNumber() {
		LocalDateTime date = LocalDateTime.now();
		String formattedDate = date.toString().substring(2, 11)
				.replace("-", "").replace("T", "-");	
		
		String randomChars = generateRandomchar(ALPHABET);
		String randomNumbers  = generateRandomchar(NUMBERS);
		
		return formattedDate + randomChars + randomNumbers;
	}
	
	private static String generateRandomchar(String characters) {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(3);
		
		for(int i = 0; i <= 2 ; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
	
	public static BigDecimal generateServiceCost(LocalDateTime begin) {
		LocalDateTime end = LocalDateTime.now();
		
		long amountOfDays = ChronoUnit.DAYS.between(begin, end);
		
		
		return amountOfDays != 0 ? new BigDecimal(dailyCost * amountOfDays).setScale(2,RoundingMode.HALF_EVEN) : 
			new BigDecimal(dailyCost).setScale(2, RoundingMode.HALF_EVEN);
		
	}
	
	public static String generateReceipt() {
		SecureRandom random = new SecureRandom();
		int index = random.nextInt(ALPHABET.length());
		char character = ALPHABET.charAt(index);
		
		LocalDateTime date = LocalDateTime.now();
		return date.toString().substring(18,29).replace('.', character);
	}
	
	public static BigDecimal calculatingFee(LocalDateTime checkIn, BigDecimal serviceCost) {
		LocalDateTime date = LocalDateTime.now();
		long amountOfDays = ChronoUnit.DAYS.between(date, checkIn);
		
		if(amountOfDays > 20) {
			return new BigDecimal("0");
		} else if(amountOfDays < 20 || amountOfDays >= 12 ) {
			return serviceCost.multiply(new BigDecimal(THIRTY_PERCENT));
		} else if(amountOfDays < 12 || amountOfDays >= 7) {
			return serviceCost.multiply(new BigDecimal(SEVENTY_PERCENT));
		} else {
			return serviceCost.multiply(new BigDecimal(NINETY_PERCENT));
		}
	}
	
}
