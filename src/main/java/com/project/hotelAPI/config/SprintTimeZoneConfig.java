package com.project.hotelAPI.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SprintTimeZoneConfig {

	public void timeZoneConfig() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
}
