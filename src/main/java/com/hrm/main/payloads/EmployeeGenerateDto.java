package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;

public class EmployeeGenerateDto {

	private String employeeId;
	private long candidateId;
	private String name;
	private String workLocation;
	private String jobTitleDesignation;
	private float serviceCommitment;
	private long serviceBreakAmount;
	private long ctc;
	private LocalDate dateOfJoining;
	private LocalDate dateOfReleasing;
	private Departments department;
	private String emailId;
	private long contactNumber;
	private EmployeeStatus employeeStatus;

	private byte[] sign;

	private byte[] appointmentLetter;

	private byte[] authorisedSignature;

	public byte[] getAuthorisedSignature() {
		return authorisedSignature;
	}

	public void setAuthorisedSignature(byte[] authorisedSignature) {
		this.authorisedSignature = authorisedSignature;
	}

	public EmployeeGenerateDto() {
		super();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
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

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getJobTitleDesignation() {
		return jobTitleDesignation;
	}

	public void setJobTitleDesignation(String jobTitleDesignation) {
		this.jobTitleDesignation = jobTitleDesignation;
	}

	public long getCtc() {
		return ctc;
	}

	public void setCtc(long ctc) {
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

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public float getServiceCommitment() {
		return serviceCommitment;
	}

	public void setServiceCommitment(float serviceCommitment) {
		this.serviceCommitment = serviceCommitment;
	}

	public long getServiceBreakAmount() {
		return serviceBreakAmount;
	}

	public void setServiceBreakAmount(long serviceBreakAmount) {
		this.serviceBreakAmount = serviceBreakAmount;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public byte[] getSign() {
		return sign;
	}

	public void setSign(byte[] sign) {
		this.sign = sign;
	}

	public byte[] getAppointmentLetter() {
		return appointmentLetter;
	}

	public void setAppointmentLetter(byte[] appointmentLetter) {
		this.appointmentLetter = appointmentLetter;
	}

}
