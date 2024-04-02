package com.hrm.models;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.ShiftRule;
import com.hrm.helper.EnumCollection.WeekRule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeWorkWeek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sno;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
