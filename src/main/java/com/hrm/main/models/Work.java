package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
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
@Table(name = "work")
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Work_Id_Sequence")
	@SequenceGenerator(name = "Work_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Work_Id_Sequence")
	private int workId;
	private String comapanyName;
	private String previousDesignation;
	private LocalDate tenureTo;
	private LocalDate tenureFrom;
	private long lastCTC;
	private String lastReportingManagerName;
	private long lastReportingManagerContactNo;
	@Column(name = "candidate_id")
	private long candidateId;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	/*
	 * private byte[] uploadOfferLetter;
	 * 
	 * @Transient public String offerLetterBase64Data;
	 * 
	 * @Lob
	 * 
	 * @Basic(fetch = FetchType.LAZY)
	 * 
	 * @Column(columnDefinition = "LONGBLOB")
	 */
	private byte[] lastAppraisalLetter;
	@Transient
	public String appraisalLetterBase64Data;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] lastMonthPaySlip;
	@Transient
	public String paySlipBase64Data;
	private DetailsSubmissionStatus workSubmissionStatus = getWorkSubmissionStatus().Pending;
	private ApprovalStatus hrExecutiveApprovalStatus = getHrExecutiveApprovalStatus().Pending;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus = getHrManagerApprovalStatus().Pending;
	private String hrManagerRemark;

	public Work() {
		super();
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public String getComapanyName() {
		return comapanyName;
	}

	public void setComapanyName(String comapanyName) {
		this.comapanyName = comapanyName;
	}

	public String getPreviousDesignation() {
		return previousDesignation;
	}

	public void setPreviousDesignation(String previousDesignation) {
		this.previousDesignation = previousDesignation;
	}

	public LocalDate getTenureTo() {
		return tenureTo;
	}

	public void setTenureTo(LocalDate tenureTo) {
		this.tenureTo = tenureTo;
	}

	public LocalDate getTenureFrom() {
		return tenureFrom;
	}

	public void setTenureFrom(LocalDate tenureFrom) {
		this.tenureFrom = tenureFrom;
	}

	public long getLastCTC() {
		return lastCTC;
	}

	public void setLastCTC(long lastCTC) {
		this.lastCTC = lastCTC;
	}

	public String getLastReportingManagerName() {
		return lastReportingManagerName;
	}

	public void setLastReportingManagerName(String lastReportingManagerName) {
		this.lastReportingManagerName = lastReportingManagerName;
	}

	public long getLastReportingManagerContactNo() {
		return lastReportingManagerContactNo;
	}

	public void setLastReportingManagerContactNo(long lastReportingManagerContactNo) {
		this.lastReportingManagerContactNo = lastReportingManagerContactNo;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public byte[] getLastAppraisalLetter() {
		return lastAppraisalLetter;
	}

	public void setLastAppraisalLetter(byte[] lastAppraisalLetter) {
		this.lastAppraisalLetter = lastAppraisalLetter;
	}

	public String getAppraisalLetterBase64Data() {
		return appraisalLetterBase64Data;
	}

	public void setAppraisalLetterBase64Data(String appraisalLetterBase64Data) {
		this.appraisalLetterBase64Data = appraisalLetterBase64Data;
	}

	public byte[] getLastMonthPaySlip() {
		return lastMonthPaySlip;
	}

	public void setLastMonthPaySlip(byte[] lastMonthPaySlip) {
		this.lastMonthPaySlip = lastMonthPaySlip;
	}

	public String getPaySlipBase64Data() {
		return paySlipBase64Data;
	}

	public void setPaySlipBase64Data(String paySlipBase64Data) {
		this.paySlipBase64Data = paySlipBase64Data;
	}

	public DetailsSubmissionStatus getWorkSubmissionStatus() {
		return workSubmissionStatus;
	}

	public void setWorkSubmissionStatus(DetailsSubmissionStatus workSubmissionStatus) {
		this.workSubmissionStatus = workSubmissionStatus;
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
