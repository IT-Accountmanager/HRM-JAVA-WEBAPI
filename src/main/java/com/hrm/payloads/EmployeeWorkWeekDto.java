package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.ShiftRule;
import com.hrm.helper.EnumCollection.WeekRule;

public class EmployeeWorkWeekDto {
	private String name;
	private ShiftRule shiftRuleSetting;
	private WeekRule weekRuleSetting;
	private LocalDate effectiveDate;
	private String employeeId;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ShiftRule getShiftRuleSetting() {
		return shiftRuleSetting;
	}
	public void setShiftRuleSetting(ShiftRule shiftRuleSetting) {
		this.shiftRuleSetting = shiftRuleSetting;
	}
	public WeekRule getWeekRuleSetting() {
		return weekRuleSetting;
	}
	public void setWeekRuleSetting(WeekRule weekRuleSetting) {
		this.weekRuleSetting = weekRuleSetting;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	

	

}
