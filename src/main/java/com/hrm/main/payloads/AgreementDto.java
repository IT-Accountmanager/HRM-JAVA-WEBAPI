package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

public class AgreementDto {
	private int agreementId;
	private long candidateId;
	private String employeeName;
	private String employeeAddress;
	private String nationality;
	private int age;
	private String permanentAddress;
	private String employerName;
	private String corporateOfficeAddress;
	private String regOfficeAddress;
	private int duration;
	private LocalDate startDate;
	private LocalDate endDate;
	private int liquidatedDamagesAmount;
	private boolean isTransferable;
	private String arbitrationVenue;
	private int noticePeriod;
	private DetailsSubmissionStatus agreementSubmissionStatus;
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus;
	private String hrManagerRemark;

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

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getCorporateOfficeAddress() {
		return corporateOfficeAddress;
	}

	public void setCorporateOfficeAddress(String corporateOfficeAddress) {
		this.corporateOfficeAddress = corporateOfficeAddress;
	}

	public String getRegOfficeAddress() {
		return regOfficeAddress;
	}

	public void setRegOfficeAddress(String regOfficeAddress) {
		this.regOfficeAddress = regOfficeAddress;
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
