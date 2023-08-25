package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class BasicInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Basic_Id_Sequence")
	@SequenceGenerator(name="Basic_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Basic_Id_Sequence")	
	private int employeeId;
	private String employeeType;
	private String employeeStatus;
	private String dateOfJoining;
	private String workLocation;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public String getProbationPeriod() {
		return probationPeriod;
	}
	public void setProbationPeriod(String probationPeriod) {
		this.probationPeriod = probationPeriod;
	}
	private String workExperience;
	private String probationPeriod;
	
}
