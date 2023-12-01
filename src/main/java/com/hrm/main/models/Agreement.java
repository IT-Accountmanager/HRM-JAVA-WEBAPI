package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "agreement")
public class Agreement {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Agreement_Id_Sequence")
	@SequenceGenerator(name = "Agreement_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Agreement_Id_Sequence")
	@Column(name = "agreement_id")
	private int agreementId;
	@Column(name = "candidate_id")
	private long candidateId;
	private String employeeName;
	private String employeeAddress;
	private String nationality;
	private int age;
	private String permanentAddress;
	private static String employerName = "Envision Integrated Services Private Limited";
	private static String corporateOfficeAddress = "#913, 9th floor, Manjeera Trinity Corporate Building, E-Seva lane, KPHB, Hyderabad – 500085";
	private static String regOfficeAddress = "#8-3-940/5/501, Tirumala Apartments, Yellareddyguda Cross Roads, Ameerpet, Hyderabad, Telangana – 500038";
	private int duration;
	private LocalDate startDate;
	private LocalDate endDate;
	private int liquidatedDamagesAmount;
	private boolean isTransferable;
	private String arbitrationVenue;
	private int noticePeriod;
	private DetailsSubmissionStatus agreementSubmissionStatus;
	private ApprovalStatus hrExecutiveApprovalStatus = getHrExecutiveApprovalStatus().Pending;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus = getHrManagerApprovalStatus().Pending;
	private String hrManagerRemark;

	public Agreement() {
		super();
	}

	public int getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(int agreementId) {
		this.agreementId = agreementId;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public static String getEmployerName() {
		return employerName;
	}

	public static void setEmployerName(String employerName) {
		Agreement.employerName = employerName;
	}

	public static String getCorporateOfficeAddress() {
		return corporateOfficeAddress;
	}

	public static void setCorporateOfficeAddress(String corporateOfficeAddress) {
		Agreement.corporateOfficeAddress = corporateOfficeAddress;
	}

	public static String getRegOfficeAddress() {
		return regOfficeAddress;
	}

	public static void setRegOfficeAddress(String regOfficeAddress) {
		Agreement.regOfficeAddress = regOfficeAddress;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getLiquidatedDamagesAmount() {
		return liquidatedDamagesAmount;
	}

	public void setLiquidatedDamagesAmount(int liquidatedDamagesAmount) {
		this.liquidatedDamagesAmount = liquidatedDamagesAmount;
	}

	public boolean isTransferable() {
		return isTransferable;
	}

	public void setTransferable(boolean isTransferable) {
		this.isTransferable = isTransferable;
	}

	public String getArbitrationVenue() {
		return arbitrationVenue;
	}

	public void setArbitrationVenue(String arbitrationVenue) {
		this.arbitrationVenue = arbitrationVenue;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public DetailsSubmissionStatus getAgreementSubmissionStatus() {
		return agreementSubmissionStatus;
	}

	public void setAgreementSubmissionStatus(DetailsSubmissionStatus agreementSubmissionStatus) {
		this.agreementSubmissionStatus = agreementSubmissionStatus;
	}

	public ApprovalStatus getHrExecutiveApprovalStatus() {
		return hrExecutiveApprovalStatus;
	}

	public void setHrExecutiveApprovalStatus(ApprovalStatus hrExecutiveApprovalStatus) {
		this.hrExecutiveApprovalStatus = hrExecutiveApprovalStatus;
	}

	public String getHrExecutiveRemark() {
		return hrExecutiveRemark;
	}

	public void setHrExecutiveRemark(String hrExecutiveRemark) {
		this.hrExecutiveRemark = hrExecutiveRemark;
	}

	public ApprovalStatus getHrManagerApprovalStatus() {
		return hrManagerApprovalStatus;
	}

	public void setHrManagerApprovalStatus(ApprovalStatus hrManagerApprovalStatus) {
		this.hrManagerApprovalStatus = hrManagerApprovalStatus;
	}

	public String getHrManagerRemark() {
		return hrManagerRemark;
	}

	public void setHrManagerRemark(String hrManagerRemark) {
		this.hrManagerRemark = hrManagerRemark;
	}

}
