package com.hrm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class NewJobs {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NewJobs_Id_Sequence")
	@SequenceGenerator(name = "NewJobs_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "NewJobs_Id_Sequence")
	private int id;
	private String date;
	private String jobTitle;
	private String qualification;
	private float experience;
	private int noOfPositions;
	private String endDate;

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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public int getNoOfPositions() {
		return noOfPositions;
	}

	public void setNoOfPositions(int noOfPositions) {
		this.noOfPositions = noOfPositions;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
