package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.models.Helper.EnumCollection.ResignationStatus;
import com.hrm.main.models.Helper.EnumCollection.Sub_Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_Id_Sequence")
	@SequenceGenerator(name = "Employee_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Employee_Id_Sequence")
	private int employeeSn;
	@Column(name = "candidate_id")
	private long candidateId;
	private String employeeId;
	private String name;
	private String jobTitle;
	private String workLocation;
	private String designation;
	private Departments department;
	private Sub_Department subDepartment;
	private String assignTo;
	private float bondPeriod;
	private long bondBreakAmount;
	private float ctc;
	private LocalDate dateOfJoining;
	private LocalDate dateOfReleasing;
	private long contactNumber;
	private String emailId;
	private EmployeeStatus employeeStatus;
	private float relevantExperience;
	private int probationPeriod;
	private String employeeType;
	private LocalDate resignationDate;
	private ResignationStatus resignationStatus;
	private int noticePeriod;
	private LocalDate lastWorkingDay;
	private Departments previouDepartment;
	private String previouDesignation;
	private LocalDate previouWorkFrom;
	private LocalDate previouWorkUpto;

	public Employee() {
		super();
	}

	public int getEmployeeSn() {
		return employeeSn;
	}

	public void setEmployeeSn(int employeeSn) {
		this.employeeSn = employeeSn;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public float getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(float bondPeriod) {
		this.bondPeriod = bondPeriod;
	}

	public long getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(long bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public LocalDate getDateOfReleasing() {
		return dateOfReleasing;
	}

	public void setDateOfReleasing(LocalDate dateOfReleasing) {
		this.dateOfReleasing = dateOfReleasing;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public float getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(float relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public int getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(int probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Sub_Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Sub_Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

	public ResignationStatus getResignationStatus() {
		return resignationStatus;
	}

	public void setResignationStatus(ResignationStatus resignationStatus) {
		this.resignationStatus = resignationStatus;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
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

}
