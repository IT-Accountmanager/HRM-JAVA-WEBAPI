package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;

public class AgreementDto {
	private int agreementId;
	private long candidateId;
	private LocalDate agreementDate;
	private String employeeName;
	private String employeeFathersName;
	private int age;
	private String religion;
	private String presentAddress;
	private String permanentAddress;
	private float serviceCommitment;
	private LocalDate tenureFrom;
	private LocalDate tenureTo;
	private String employerName;
	private String corporateOfficeAddress;
	private String regOfficeAddress;
	private String chequeNo;

	/*
	 * private float duration; private LocalDate startDate; private LocalDate
	 * endDate;
	 */
	private long serviceBreakAmount;
	private String custodyOf;
	private DetailsSubmissionStatus agreementSubmissionStatus;
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus;
	private String hrManagerRemark;
	private byte[] sign;
	private String signBase64;
	private byte[] leftHandThumbImpression;
	private String leftHandThumbImpressionBase64;

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

	public LocalDate getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(LocalDate agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeFathersName() {
		return employeeFathersName;
	}

	public void setEmployeeFathersName(String employeeFathersName) {
		this.employeeFathersName = employeeFathersName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public float getServiceCommitment() {
		return serviceCommitment;
	}

	public void setServiceCommitment(float serviceCommitment) {
		this.serviceCommitment = serviceCommitment;
	}

	public LocalDate getTenureFrom() {
		return tenureFrom;
	}

	public void setTenureFrom(LocalDate tenureFrom) {
		this.tenureFrom = tenureFrom;
	}

	public LocalDate getTenureTo() {
		return tenureTo;
	}

	public void setTenureTo(LocalDate tenureTo) {
		this.tenureTo = tenureTo;
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

	/*
	 * public float getDuration() { return duration; }
	 * 
	 * public void setDuration(float duration) { this.duration = duration; }
	 * 
	 * public LocalDate getStartDate() { return startDate; }
	 * 
	 * public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
	 * 
	 * public LocalDate getEndDate() { return endDate; }
	 * 
	 * public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
	 */

	public long getServiceBreakAmount() {
		return serviceBreakAmount;
	}

	public void setServiceBreakAmount(long serviceBreakAmount) {
		this.serviceBreakAmount = serviceBreakAmount;
	}

	public String getCustodyOf() {
		return custodyOf;
	}

	public void setCustodyOf(String custodyOf) {
		this.custodyOf = custodyOf;
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

	public byte[] getSign() {
		return sign;
	}

	public void setSign(byte[] sign) {
		this.sign = sign;
	}

	public String getSignBase64() {
		return signBase64;
	}

	public void setSignBase64(String signBase64) {
		this.signBase64 = signBase64;
	}

	public byte[] getLeftHandThumbImpression() {
		return leftHandThumbImpression;
	}

	public void setLeftHandThumbImpression(byte[] leftHandThumbImpression) {
		this.leftHandThumbImpression = leftHandThumbImpression;
	}

	public String getLeftHandThumbImpressionBase64() {
		return leftHandThumbImpressionBase64;
	}

	public void setLeftHandThumbImpressionBase64(String leftHandThumbImpressionBase64) {
		this.leftHandThumbImpressionBase64 = leftHandThumbImpressionBase64;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

}
