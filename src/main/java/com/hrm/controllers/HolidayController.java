package com.hrm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.hrm.models.Holiday;
import com.hrm.services.IHolidayService;

//@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/holiday")
public class HolidayController {

	public static final Logger logger = LoggerFactory.getLogger(HolidayController.class);

	@Autowired
	IHolidayService holidayService;

	@PostMapping("/add")
	public String add(@RequestBody ArrayNode holiday) {
		logger.info("Inside Holiday add Method :{}", holiday);

		String result = null;

		try {
			result = this.holidayService.add(holiday);
		} catch (Exception e) {

			logger.error("Error Occure in Add Holiday Method ::", e);

		}
		return result;
	}

	@GetMapping("/getallholidays")
	public ResponseEntity<List<Holiday>> getListOfHolidays() {
		List<Holiday> holidays = holidayService.getListOfHolidays();
		return new ResponseEntity<>(holidays, HttpStatus.OK);
	}

}
