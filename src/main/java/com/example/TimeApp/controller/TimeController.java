package com.example.TimeApp.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

	private static final Logger logger = LoggerFactory.getLogger(TimeController.class);
	private static final Map<String, String> cityTimezoneMap = new HashMap<>();

	static {
		cityTimezoneMap.put("mumbai", "Asia/Kolkata");
		cityTimezoneMap.put("delhi", "Asia/Kolkata");
		cityTimezoneMap.put("bangalore", "Asia/Kolkata");
		cityTimezoneMap.put("hyderabad", "Asia/Kolkata");
		cityTimezoneMap.put("ahmedabad", "Asia/Kolkata");
		cityTimezoneMap.put("chennai", "Asia/Kolkata");
		cityTimezoneMap.put("kolkata", "Asia/Kolkata");
		cityTimezoneMap.put("pune", "Asia/Kolkata");
		cityTimezoneMap.put("jaipur", "Asia/Kolkata");
		cityTimezoneMap.put("surat", "Asia/Kolkata");
		cityTimezoneMap.put("lucknow", "Asia/Kolkata");
		cityTimezoneMap.put("kanpur", "Asia/Kolkata");
		cityTimezoneMap.put("nagpur", "Asia/Kolkata");
		cityTimezoneMap.put("visakhapatnam", "Asia/Kolkata");
		cityTimezoneMap.put("bhopal", "Asia/Kolkata");
		cityTimezoneMap.put("patna", "Asia/Kolkata");
		cityTimezoneMap.put("ludhiana", "Asia/Kolkata");
		cityTimezoneMap.put("agra", "Asia/Kolkata");
		cityTimezoneMap.put("nashik", "Asia/Kolkata");
		cityTimezoneMap.put("vadodara", "Asia/Kolkata");
		cityTimezoneMap.put("pondicherry", "Asia/Kolkata");
		cityTimezoneMap.put("meerut", "Asia/Kolkata");
		cityTimezoneMap.put("raipur", "Asia/Kolkata");
		cityTimezoneMap.put("jodhpur", "Asia/Kolkata");
		cityTimezoneMap.put("gwalior", "Asia/Kolkata");
		cityTimezoneMap.put("coimbatore", "Asia/Kolkata");
		cityTimezoneMap.put("vijayawada", "Asia/Kolkata");
		cityTimezoneMap.put("madurai", "Asia/Kolkata");
		cityTimezoneMap.put("guwahati", "Asia/Kolkata");
		cityTimezoneMap.put("tiruchirappalli", "Asia/Kolkata");
		cityTimezoneMap.put("hubli-dharwad", "Asia/Kolkata");
		cityTimezoneMap.put("mysore", "Asia/Kolkata");
		cityTimezoneMap.put("mangalore", "Asia/Kolkata");
		cityTimezoneMap.put("thiruvananthapuram", "Asia/Kolkata");
		cityTimezoneMap.put("salem", "Asia/Kolkata");
		cityTimezoneMap.put("warangal", "Asia/Kolkata");
		cityTimezoneMap.put("malegaon", "Asia/Kolkata");
		cityTimezoneMap.put("gaya", "Asia/Kolkata");
		cityTimezoneMap.put("jalgaon", "Asia/Kolkata");
		cityTimezoneMap.put("udaipur", "Asia/Kolkata");
		cityTimezoneMap.put("loni", "Asia/Kolkata");
		cityTimezoneMap.put("siliguri", "Asia/Kolkata");
		cityTimezoneMap.put("jhansi", "Asia/Kolkata");
		cityTimezoneMap.put("ulhasnagar", "Asia/Kolkata");
		cityTimezoneMap.put("jammu", "Asia/Kolkata");
		cityTimezoneMap.put("sangli-miraj & kupwad", "Asia/Kolkata");
		cityTimezoneMap.put("mira-bhayandar", "Asia/Kolkata");
		cityTimezoneMap.put("bijapur", "Asia/Kolkata");
		cityTimezoneMap.put("kolhapur", "Asia/Kolkata");
		cityTimezoneMap.put("ambala", "Asia/Kolkata");
		cityTimezoneMap.put("muzaffarnagar", "Asia/Kolkata");
		cityTimezoneMap.put("jalandhar", "Asia/Kolkata");
		cityTimezoneMap.put("shahjahanpur", "Asia/Kolkata");
		cityTimezoneMap.put("shimoga", "Asia/Kolkata");
		cityTimezoneMap.put("tuticorin", "Asia/Kolkata");
		cityTimezoneMap.put("calicut", "Asia/Kolkata");
		cityTimezoneMap.put("akola", "Asia/Kolkata");
		cityTimezoneMap.put("bhagalpur", "Asia/Kolkata");
		cityTimezoneMap.put("jamnagar", "Asia/Kolkata");
		cityTimezoneMap.put("udaipur", "Asia/Kolkata");
		cityTimezoneMap.put("bhiwandi", "Asia/Kolkata");
		cityTimezoneMap.put("aurangabad", "Asia/Kolkata");
		cityTimezoneMap.put("rajkot", "Asia/Kolkata");
		cityTimezoneMap.put("jalgaon", "Asia/Kolkata");
		cityTimezoneMap.put("dhanbad", "Asia/Kolkata");
		cityTimezoneMap.put("allahabad", "Asia/Kolkata");
		cityTimezoneMap.put("amritsar", "Asia/Kolkata");
		cityTimezoneMap.put("jamshedpur", "Asia/Kolkata");
	}

	@GetMapping("/getTime")
	public String getTime(@RequestParam("city") String city) {
		String timezone = findTimezoneForCity(city);
		if (timezone == null) {
			return "Error: Timezone not found for " + city;
		}

		try {
			ZonedDateTime localDateTime = ZonedDateTime.now(ZoneId.of(timezone));
			return "Current time in " + city + ": " + localDateTime.toString();
		} catch (Exception e) {
			logger.error("Error fetching time: ", e);
			return "Error: " + e.getMessage();
		}
	}

	private String findTimezoneForCity(String city) {
		Set<String> availableZones = ZoneId.getAvailableZoneIds();
		String normalizedCity = city.toLowerCase().replace(" ", "_");

		// First, check the manual mapping
		if (cityTimezoneMap.containsKey(normalizedCity)) {
			return cityTimezoneMap.get(normalizedCity);
		}

		// Fall back to the automatic lookup
		for (String zone : availableZones) {
			if (zone.toLowerCase().contains(normalizedCity)) {
				return zone;
			}
		}
		return null;
	}
}
