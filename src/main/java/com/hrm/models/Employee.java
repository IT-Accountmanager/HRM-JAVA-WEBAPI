package com.hrm.models;

import java.time.Duration;
import java.time.LocalDate;

import com.hrm.helper.EnumCollection.AppraisalQuater;
import com.hrm.helper.EnumCollection.BloodGroup;
import com.hrm.helper.EnumCollection.CategoryControl;
import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.EmployeeCategory;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.helper.EnumCollection.ManagerType;
import com.hrm.helper.EnumCollection.NoticePeriod;
import com.hrm.helper.EnumCollection.ProbationPeriod;
import com.hrm.helper.EnumCollection.ResignationStatus;
import com.hrm.helper.EnumCollection.WorkLocation;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private EmployeeCategory employeeCategory = EmployeeCategory.Working;
	private long contactNumber;
	private String emailId;
	private LocalDate dateOfJoining;
	private Departments department;
	private Departments.Department subDepartment;
	private String assignTo;
	private Designation designation;//
	private String totalExperience;
	private long joinedCtc;//
	private long currentCtc;//
	private float serviceCommitment;
	private Duration numberOfWorkingDays;
	private AppraisalQuater nextApprisalQuater;
	private String uanNumber;
	private String yearOfPassout;
	private String jobTitle;
	private WorkLocation workLocation;//
	private long bondBreakAmount;//
	private float ctc;//
	private LocalDate dateOfReleasing;
	private float workExperience;
	private float relevantExperience;
	// @Enumerated(EnumType.STRING)
	private ProbationPeriod probationPeriod;
	private String employeeType;
	private LocalDate resignationDate;
	private ResignationStatus resignationStatus;
	private NoticePeriod noticePeriod;
	private LocalDate lastWorkingDay;
	private Departments previouDepartment;
	private String previouDesignation;
	private LocalDate previouWorkFrom;
	private LocalDate previouWorkUpto;
	private String manager;
	private ManagerType managerType;
	private LocalDate managerTo;
	private LocalDate managerFrom;
	private CategoryControl categoryControl;
	private boolean imported;

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

	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(EmployeeCategory employeeCategory) {
		this.employeeCategory = employeeCategory;
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

	public NoticePeriod getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(NoticePeriod noticePeriod) {
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

	public long getJoinedCtc() {
		return joinedCtc;
	}

	public void setJoinedCtc(long joinedCtc) {
		this.joinedCtc = joinedCtc;
	}

	public long getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(long currentCtc) {
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

	public WorkLocation getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(WorkLocation workLocation) {
		this.workLocation = workLocation;
	}

	public CategoryControl getCategoryControl() {
		return categoryControl;
	}

	public void setCategoryControl(CategoryControl categoryControl) {
		this.categoryControl = categoryControl;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

}
