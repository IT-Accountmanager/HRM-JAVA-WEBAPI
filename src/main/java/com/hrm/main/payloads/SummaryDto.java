package com.hrm.main.payloads;

import java.time.Duration;
import java.time.LocalDate;

import com.hrm.main.models.AddressDetails;
import com.hrm.main.models.PermanentAddress;
import com.hrm.main.models.PresentAddress;
import com.hrm.main.models.Helper.EnumCollection.AppraisalQuater;
import com.hrm.main.models.Helper.EnumCollection.BloodGroup;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Designation;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;

public class SummaryDto {
	private long candidateId;
	private String employeeId;
	private String name;
	private EmployeeStatus employeeStatus;
	private long contactNumber;
	private String emailId;
	private LocalDate dateOfJoining;
	private Departments department;
	private Departments.Department subDepartment;
	private String assignTo;
	private Designation designation;
	private String totalExperience;
	private long joinedCtc;
	private String currentCtc;
	private float serviceCommitment;
	private Duration numberOfWorkingDays;
	private AppraisalQuater nextApprisalQuater;
	private LocalDate dateOfBirth;
	private BloodGroup bloodGroup;
	private String fatherName;
	private String emergencyContact;
	private String permanentAddress;
	private String temporaryAddress;
	private String aadharCardNumber;
	private String panCardNumber;
	private String uanNumber;
	private String bankAccountNumber;
	private String qualification;
	private String specialization;
	private String yearOfPassout;
	private String resignationDate;
	private String actualLastWorkingDay;

	// private long serviceBreakAmount;
	private float relevantExperience = 0.0f;
	private String workLocation;
	/* private byte[] appointmentLetter; */

	public SummaryDto() {
		super();
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public float getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(float relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public long getJoinedCtc() {
		return joinedCtc;
	}

	public void setJoinedCtc(long joinedCtc) {
		this.joinedCtc = joinedCtc;
	}

	public String getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(String currentCtc) {
		this.currentCtc = currentCtc;
	}

	public float getServiceCommitment() {
		return serviceCommitment;
	}

	public void setServiceCommitment(float serviceCommitment) {
		this.serviceCommitment = serviceCommitment;
	}

	public Duration getNumberOfWorkingDays() {
		return numberOfWorkingDays;
	}

	public void setNumberOfWorkingDays(Duration numberOfWorkingDays) {
		this.numberOfWorkingDays = numberOfWorkingDays;
	}

	public AppraisalQuater getNextApprisalQuater() {
		return nextApprisalQuater;
	}

	public void setNextApprisalQuater(AppraisalQuater nextApprisalQuater) {
		this.nextApprisalQuater = nextApprisalQuater;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getYearOfPassout() {
		return yearOfPassout;
	}

	public void setYearOfPassout(String yearOfPassout) {
		this.yearOfPassout = yearOfPassout;
	}

	public String getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(String resignationDate) {
		this.resignationDate = resignationDate;
	}

	public String getActualLastWorkingDay() {
		return actualLastWorkingDay;
	}

	public void setActualLastWorkingDay(String actualLastWorkingDay) {
		this.actualLastWorkingDay = actualLastWorkingDay;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

}
