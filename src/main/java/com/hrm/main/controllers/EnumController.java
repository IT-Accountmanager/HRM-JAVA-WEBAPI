package com.hrm.main.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Helper.EnumCollection.CategoryControll;
import com.hrm.main.models.Helper.EnumCollection.CourseType;
import com.hrm.main.models.Helper.EnumCollection.Gender;
import com.hrm.main.models.Helper.EnumCollection.MaritalStatus;
import com.hrm.main.models.Helper.EnumCollection.Relationship;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Enum")
public class EnumController {

	@GetMapping("/genders")
	public Gender[] getGenders() {
		return Gender.values();
	}

	@GetMapping("/courses")
	public CourseType[] getCourses() {
		return CourseType.values();
	}

	@GetMapping("/marital-status")
	public MaritalStatus[] getMaritalStatus() {
		return MaritalStatus.values();
	}
	
	@GetMapping("/category-controll")
	public CategoryControll[] getCategoryControll() {
		return CategoryControll.values();
	}
	
	@GetMapping("/relationship")
	public Relationship[] getRelationship() {
		return Relationship.values();
	}
}
