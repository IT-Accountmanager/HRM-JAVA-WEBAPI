package com.hrm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.models.Holiday;
import com.hrm.services.IHolidayService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/holiday")
public class HolidayController {

	@Autowired
	IHolidayService holidayService;

	@PostMapping("/add")
	public String add(@RequestBody List<Holiday> holiday) {
		String result = this.holidayService.add(holiday);
		return result;
	}
	
	 @GetMapping("/getallholidays")
	    public ResponseEntity<List<Holiday>> getListOfHolidays() {
	        List<Holiday> holidays = holidayService.getListOfHolidays();
	        return new ResponseEntity<>(holidays, HttpStatus.OK);
	    }
	 

}
