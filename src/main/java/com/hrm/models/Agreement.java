package com.hrm.models;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
	private LocalDate agreementDate;
	private String employeeName;
	private String employeeFathersOrHusbandName;
	private int age;
	private String religion;
	private String presentAddress;
	private String permanentAddress;
	private float serviceCommitment;
	private LocalDate tenureFrom;
	private LocalDate tenureTo;
	private static String employerName = "Envision Integrated Services Private Limited";
	private static String corporateOfficeAddress = "#913, 9th floor, Manjeera Trinity Corporate Building, E-Seva lane, KPHB, Hyderabad – 500085";
	private static String regOfficeAddress = "#8-3-940/5/501, Tirumala Apartments, Yellareddyguda Cross Roads, Ameerpet, Hyderabad, Telangana – 500038";
	/*
	 * private float duration; private LocalDate startDate; private LocalDate
	 * endDate;
	 */
	private long serviceBreakAmount;
	private String chequeNo1;
	private String chequeNo2;
	private DetailsSubmissionStatus agreementSubmissionStatus = DetailsSubmissionStatus.Pending;
	private ApprovalStatus hrExecutiveApprovalStatus = ApprovalStatus.Pending;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus = ApprovalStatus.Pending;
	private String hrManagerRemark;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] sign;
	@Transient
	public String signBase64;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] leftHandThumbImpression;
	@Transient
	public String leftHandThumbImpressionBase64;

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

	public String getEmployeeFathersOrHusbandName() {
		return employeeFathersOrHusbandName;
	}

	public void setEmployeeFathersOrHusbandName(String employeeFathersOrHusbandName) {
		this.employeeFathersOrHusbandName = employeeFathersOrHusbandName;
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

	public String getChequeNo1() {
		return chequeNo1;
	}

	public void setChequeNo1(String chequeNo1) {
		this.chequeNo1 = chequeNo1;
	}

	public String getChequeNo2() {
		return chequeNo2;
	}

	public void setChequeNo2(String chequeNo2) {
		this.chequeNo2 = chequeNo2;
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

	public byte[] getLeftHandThumbImpression() {
		return leftHandThumbImpression;
	}

	public void setLeftHandThumbImpression(byte[] leftHandThumbImpression) {
		this.leftHandThumbImpression = leftHandThumbImpression;
	}

}
