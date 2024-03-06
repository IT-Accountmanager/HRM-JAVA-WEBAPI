package com.hrm.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.Helper.EnumCollection.CategoryControl;
import com.hrm.Helper.EnumCollection.CourseType;
import com.hrm.Helper.EnumCollection.EmployeeCategory;
import com.hrm.Helper.EnumCollection.Gender;
import com.hrm.Helper.EnumCollection.MaritalStatus;
import com.hrm.Helper.EnumCollection.NoticePeriod;
import com.hrm.Helper.EnumCollection.Relationship;
import com.hrm.Helper.EnumCollection.Resignation;
import com.hrm.Helper.EnumCollection.ShiftRule;
import com.hrm.Helper.EnumCollection.WeekRule;

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

	@GetMapping("/weekrule")
	public WeekRule[] getWeek() {
		return WeekRule.values();
	}

	@GetMapping("/resignation")
	public Resignation[] getResignation() {
		return Resignation.values();
	}

	@GetMapping("/noticePeriod")
	public NoticePeriod[] getNotice() {
		return NoticePeriod.values();
	}

	@GetMapping("/shiftrule")
	public ShiftRule[] getShift() {
		return ShiftRule.values();
	}

	@GetMapping("/category")
	public EmployeeCategory[] getCategory() {
		return EmployeeCategory.values();
	}

	@GetMapping("/categoryControl")
	public CategoryControl[] getCategoryControl() {
		return CategoryControl.values();
	}

	@GetMapping("/relationship")
	public Relationship[] getRelationship() {
		return Relationship.values();
	}
}
