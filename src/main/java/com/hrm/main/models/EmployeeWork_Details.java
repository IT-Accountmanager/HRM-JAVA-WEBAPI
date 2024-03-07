package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.AppraisalQuater;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Designation;
import com.hrm.main.models.Helper.EnumCollection.ManagerType;
import com.hrm.main.models.Helper.EnumCollection.ProbationPeriod;

public class EmployeeWork_Details {
	
	private Departments department;
	private Departments.Department subDepartment;
	private String assignTo;
	private Designation designation;
	private String totalExperience;
	private long currentCtc;
	private AppraisalQuater nextApprisalQuater;
	private String jobTitle;
	private float ctc;
	private ProbationPeriod probationPeriod;
	private String employeeType;
	private LocalDate lastWorkingDay;
	private Departments previouDepartment;
	private String previouDesignation;
	private LocalDate previouWorkFrom;
	private LocalDate previouWorkUpto;
	private String manager;
	private ManagerType managerType;
	private LocalDate managerTo;
	private LocalDate managerFrom;
	
	
	public Departments getDepartment() {
		return department;
	}
	public void setDepartment(Departments department) {
		this.department = department;
	}
	public Departments.Department getSubDepartment() {
		return subDepartment;
	}
	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public String getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}
	public long getCurrentCtc() {
		return currentCtc;
	}
	public void setCurrentCtc(long currentCtc) {
		this.currentCtc = currentCtc;
	}
	public AppraisalQuater getNextApprisalQuater() {
		return nextApprisalQuater;
	}
	public void setNextApprisalQuater(AppraisalQuater nextApprisalQuater) {
		this.nextApprisalQuater = nextApprisalQuater;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public float getCtc() {
		return ctc;
	}
	public void setCtc(float ctc) {
		this.ctc = ctc;
	}
	public ProbationPeriod getProbationPeriod() {
		return probationPeriod;
	}
	public void setProbationPeriod(ProbationPeriod probationPeriod) {
		this.probationPeriod = probationPeriod;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public LocalDate getLastWorkingDay() {
		return lastWorkingDay;
	}
	public void setLastWorkingDay(LocalDate lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}
	public Departments getPreviouDepartment() {
		return previouDepartment;
	}
	public void setPreviouDepartment(Departments previouDepartment) {
		this.previouDepartment = previouDepartment;
	}
	public String getPreviouDesignation() {
		return previouDesignation;
	}
	public void setPreviouDesignation(String previouDesignation) {
		this.previouDesignation = previouDesignation;
	}
	public LocalDate getPreviouWorkFrom() {
		return previouWorkFrom;
	}
	public void setPreviouWorkFrom(LocalDate previouWorkFrom) {
		this.previouWorkFrom = previouWorkFrom;
	}
	public LocalDate getPreviouWorkUpto() {
		return previouWorkUpto;
	}
	public void setPreviouWorkUpto(LocalDate previouWorkUpto) {
		this.previouWorkUpto = previouWorkUpto;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public ManagerType getManagerType() {
		return managerType;
	}
	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
	public LocalDate getManagerTo() {
		return managerTo;
	}
	public void setManagerTo(LocalDate managerTo) {
		this.managerTo = managerTo;
	}
	public LocalDate getManagerFrom() {
		return managerFrom;
	}
	public void setManagerFrom(LocalDate managerFrom) {
		this.managerFrom = managerFrom;
	}
	
	

}
