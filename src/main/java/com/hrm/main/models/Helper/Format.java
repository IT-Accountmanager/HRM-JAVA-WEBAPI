package com.hrm.main.models.Helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Format {

	public String getFormattedDateOfJoining(LocalDate date) {
		if (date != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			return date.format(formatter);
		}
		return null;
	}
}
