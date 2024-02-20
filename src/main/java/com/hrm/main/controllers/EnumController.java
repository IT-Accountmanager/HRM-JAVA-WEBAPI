package com.hrm.main.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Helper.EnumCollection.CategoryControl;
import com.hrm.main.models.Helper.EnumCollection.CourseType;
import com.hrm.main.models.Helper.EnumCollection.EmployeeCategory;
import com.hrm.main.models.Helper.EnumCollection.Gender;
import com.hrm.main.models.Helper.EnumCollection.MaritalStatus;
import com.hrm.main.models.Helper.EnumCollection.NoticePeriod;
import com.hrm.main.models.Helper.EnumCollection.Resignation;
import com.hrm.main.models.Helper.EnumCollection.ShiftRule;
import com.hrm.main.models.Helper.EnumCollection.WeekRule;

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

}
