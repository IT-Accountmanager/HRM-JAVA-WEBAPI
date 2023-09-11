package com.hrm.main.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "agreement")

public class Agreement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agreement_Id_Sequence")
	@SequenceGenerator(name = "agreement_Id_Sequence", initialValue = 1,allocationSize = 1, sequenceName = "agreement_Id_Sequence")
	
	private int id;
	private int employeeId;
	private String employeeName;
	private LocalDate date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public LocalDate getDate() {
		return date;
	}
	public Agreement(int id, int employeeId, String employeeName, LocalDate date) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.date = date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
		
}
