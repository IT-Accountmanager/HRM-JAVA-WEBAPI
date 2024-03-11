package com.hrm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Regularization {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Regularization_seq")
	@SequenceGenerator(name = "Regularization_seq", initialValue = 1, allocationSize = 1, sequenceName = "Regularization_seq")
	private int id;
	private String date;
	private String typeOfRegularization;
	private float inTime;
	private float outTime;
	private float workDuration;
	private float billableHours;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTypeOfRegularization() {
		return typeOfRegularization;
	}

	public void setTypeOfRegularization(String typeOfRegularization) {
		this.typeOfRegularization = typeOfRegularization;
	}

	public float getInTime() {
		return inTime;
	}

	public void setInTime(float inTime) {
		this.inTime = inTime;
	}

	public float getOutTime() {
		return outTime;
	}

	public void setOutTime(float outTime) {
		this.outTime = outTime;
	}

	public float getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(float workDuration) {
		this.workDuration = workDuration;
	}

	public float getBillableHours() {
		return billableHours;
	}

	public void setBillableHours(float billableHours) {
		this.billableHours = billableHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
