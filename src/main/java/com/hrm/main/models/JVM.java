package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class JVM {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JVM_Det_seq")
	@SequenceGenerator(name = "JVM_seq", initialValue = 1, allocationSize = 1, sequenceName = "JVM_Det_seq")
	private int id;
	private String fy;
	private String quater;
	private String remarks;
	private String department;
	private String subdepartment;
	private String softwareSkills;
	private String technicalSkills;
	private String communicationSkills;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFy() {
		return fy;
	}

	public void setFy(String fy) {
		this.fy = fy;
	}

	public String getQuater() {
		return quater;
	}

	public void setQuater(String quater) {
		this.quater = quater;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubdepartment() {
		return subdepartment;
	}

	public void setSubdepartment(String subdepartment) {
		this.subdepartment = subdepartment;
	}

	public String getSoftwareSkills() {
		return softwareSkills;
	}

	public void setSoftwareSkills(String softwareSkills) {
		this.softwareSkills = softwareSkills;
	}

	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public String getCommunicationSkills() {
		return communicationSkills;
	}

	public void setCommunicationSkills(String communicationSkills) {
		this.communicationSkills = communicationSkills;
	}

}
