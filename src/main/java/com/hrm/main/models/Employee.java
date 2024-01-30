package com.hrm.main.models;

import java.time.Duration;
import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.AppraisalQuater;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.models.Helper.EnumCollection.ManagerType;
import com.hrm.main.models.Helper.EnumCollection.ProbationPeriod;
import com.hrm.main.models.Helper.EnumCollection.ResignationStatus;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	private EmployeeStatus employeeStatus;
	private long contactNumber;
	private String emailId;
	private LocalDate dateOfJoining;
	private Departments department;
	private Departments.Department subDepartment;
	//private String assignTo;
	private String designation;
	private String totalExperience;
	private String joinedCtc;
	private String currentCtc;
	private float serviceCommitment;
	private Duration numberOfWorkingDays;
	private AppraisalQuater nextApprisalQuater;
	private String uanNumber;
	private String yearOfPassout;
	private String jobTitle;
	private String workLocation;
	private long bondBreakAmount;
	private float ctc;
	private LocalDate dateOfReleasing;
	private float workExperience;
	private float relevantExperience;
	// @Enumerated(EnumType.STRING)
	private ProbationPeriod probationPeriod;
	private String employeeType;
	private LocalDate resignationDate;
	private ResignationStatus resignationStatus;
	private int noticePeriod;
	private LocalDate lastWorkingDay;
	private Departments previouDepartment;
	private String previouDesignation;
	private LocalDate previouWorkFrom;
	private LocalDate previouWorkUpto;
	private String password;
	private String manager;
	private ManagerType managerType;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] authorisedSignature;
	public String base64Data;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] sign;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] appointmentLetter;

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

	/*
	 * public String getAssignTo() { return assignTo; }
	 * 
	 * public void setAssignTo(String assignTo) { this.assignTo = assignTo; }
	 */

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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/*
	 * public Sub_Department getSubDepartment() { return subDepartment; }
	 * 
	 * public void setSubDepartment(Sub_Department subDepartment) {
	 * this.subDepartment = subDepartment; }
	 */

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

	public float getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(float workExperience) {
		this.workExperience = workExperience;
	}

	public byte[] getAuthorisedSignature() {
		return authorisedSignature;
	}

	public void setAuthorisedSignature(byte[] authorisedSignature) {
		this.authorisedSignature = authorisedSignature;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getBase64Data() {
		return base64Data;
	}

	public void setBase64Data(String base64Data) {
		this.base64Data = base64Data;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getJoinedCtc() {
		return joinedCtc;
	}

	public void setJoinedCtc(String joinedCtc) {
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

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public String getYearOfPassout() {
		return yearOfPassout;
	}

	public void setYearOfPassout(String yearOfPassout) {
		this.yearOfPassout = yearOfPassout;
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

}
