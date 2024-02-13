package com.hrm.main.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Helper.EnumCollection.Category;
import com.hrm.main.models.Helper.EnumCollection.NoticePeriod;
import com.hrm.main.models.Helper.EnumCollection.Resignation;
import com.hrm.main.models.Helper.EnumCollection.ShiftRule;
import com.hrm.main.models.Helper.EnumCollection.WeekRule;




@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/enum")

public class Enum_Controller {
	
	@GetMapping("/weekrule")
	public WeekRule[]getweek(){
		return WeekRule.values();
	}
	
	@GetMapping("/resignation")
	public Resignation[]getresignation(){
		return Resignation.values();
	}
	
	@GetMapping("/noticeperiod")
	public NoticePeriod[]getnotice(){
		return NoticePeriod.values();
	}
	
	@GetMapping("/shiftrule")
	public ShiftRule[]getshift(){
		return ShiftRule.values();
	}
	
	@GetMapping("/category")
	public Category[]getcategory(){
		return Category.values();
	}

}
