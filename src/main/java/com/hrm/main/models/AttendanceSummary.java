package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class AttendanceSummary {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_summary_id_seq")
	@SequenceGenerator(name = "attendance_summary_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "attendance_summary_id_seq")
	private int id;
	private String attendanceStatus;
	private String date;
	private String inTime;
	private String outTime;
	private String manager;
	private String projectId;
	private String task;
	private int billableHours;
	private String workStatus;
	private String status;
	private String breakDuration;
	private String totalHours;
	private String shift;
	private String extraWorkingHours;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getBillableHours() {
		return billableHours;
	}

	public void setBillableHours(int billableHours) {
		this.billableHours = billableHours;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBreakDuration() {
		return breakDuration;
	}

	public void setBreakDuration(String breakDuration) {
		this.breakDuration = breakDuration;
	}

	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getExtraWorkingHours() {
		return extraWorkingHours;
	}

	public void setExtraWorkingHours(String extraWorkingHours) {
		this.extraWorkingHours = extraWorkingHours;
	}

}
