package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity

public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Work_Id_Sequence")
	@SequenceGenerator(name = "Work_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Work_Id_Sequence")
	private int id;
	private String comapanyName;
	private String employeeId;
	private String designation;
	private String employeeType;
	private String location;
	private String joinedDate;
	private String relievedDate;
	private String tenure;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComapanyName() {
		return comapanyName;
	}

	public void setComapanyName(String comapanyName) {
		this.comapanyName = comapanyName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getRelievedDate() {
		return relievedDate;
	}

	public void setRelievedDate(String relievedDate) {
		this.relievedDate = relievedDate;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

}
