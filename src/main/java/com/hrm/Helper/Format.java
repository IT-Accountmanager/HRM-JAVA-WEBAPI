package com.hrm.Helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Format {

	public static String getFormattedDate(LocalDate date) {
		if (date != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			return date.format(formatter);
		}
		return null;
	}
}
