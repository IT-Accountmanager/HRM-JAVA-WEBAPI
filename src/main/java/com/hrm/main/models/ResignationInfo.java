package com.hrm.main.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ResignationInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resign_info_id_seq")
	@SequenceGenerator(name = "resign_info_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "resign_info_id_seq")
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	private WorkHistory workHistory;
	private String resignationDate;
	private String resignationStatus;
	private int noticePeriod;
	private String lastWorkingDay;

	public WorkHistory getWorkHistory() {
		return workHistory;
	}

	public void setWorkHistory(WorkHistory workHistory) {
		this.workHistory = workHistory;
	}

	public String getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(String resignationDate) {
		this.resignationDate = resignationDate;
	}

	public String getResignationStatus() {
		return resignationStatus;
	}

	public void setResignationStatus(String resignationStatus) {
		this.resignationStatus = resignationStatus;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getLastWorkingDay() {
		return lastWorkingDay;
	}

	public void setLastWorkingDay(String lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}

}
