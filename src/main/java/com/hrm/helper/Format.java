package com.hrm.helper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Format {

	public static String getFormattedDate(LocalDate date) {
		if (date != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			return date.format(formatter);
		}
		return null;
	}

	public static String getFormattedTime(LocalTime time) {
		if (time != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
			return time.format(formatter);
		}
		return null;
	}

	public static String getFormattedWorkHours(Duration duration) {
		if (duration != null) {

			long hours = duration.toHours();
			long min = duration.minusHours(hours).toMinutes();
			long sec = duration.minusHours(hours).minusMinutes(min).getSeconds();

			String formattedDuration = String.format("%02d:%02d:%02d", hours, min, sec);
			return formattedDuration;
		}
		return null;
	}

}
