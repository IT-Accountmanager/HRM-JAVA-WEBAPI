package com.hrm.main.models;

import java.time.LocalDate;
import java.time.LocalTime;
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
	/*
	 * LocalDate dt = LocalDate.now(); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("dd-mm-yyyy");
	 */
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private String manager;
	private String projectId;
	private String task;
	private int billableHours;
	private String workStatus;
	private String status;
	private int breakDuration;
	private int totalHours;
	private String shift;
	private int extraWorkingHours;

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
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

	public int getBreakDuration() {
		return breakDuration;
	}

	public void setBreakDuration(int breakDuration) {
		this.breakDuration = breakDuration;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public int getExtraWorkingHours() {
		return extraWorkingHours;
	}

	public void setExtraWorkingHours(int extraWorkingHours) {
		this.extraWorkingHours = extraWorkingHours;
	}

}
