package com.hrm.main.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity

public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Work_Id_Sequence")
	@SequenceGenerator(name = "Work_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Work_Id_Sequence")
	private int workId;
	private String comapanyName;
	private String employeeId;
	private String designation;
	private String employeeType;
	private String location;
	private String joinedDate;
	private String relievedDate;
	private String tenure;
	private String contactName;
	private long contactNo;
	private String emailId;
	private String ctc;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] uploadOfferLetter;
	@Transient
	public String offerLetterBase64Data;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] appraisalLetter;
	@Transient
	public String appraisalLetterBase64Data;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] relievedLetter;
	@Transient
	public String relievedLetterBase64Data;

	

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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getRelievedDate() {
		return relievedDate;
	}

	public void setRelievedDate(String relievedDate) {
		this.relievedDate = relievedDate;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	public byte[] getUploadOfferLetter() {
		return uploadOfferLetter;
	}

	public void setUploadOfferLetter(byte[] uploadOfferLetter) {
		this.uploadOfferLetter = uploadOfferLetter;
	}

	public byte[] getAppraisalLetter() {
		return appraisalLetter;
	}

	public void setAppraisalLetter(byte[] appraisalLetter) {
		this.appraisalLetter = appraisalLetter;
	}

	public byte[] getRelievedLetter() {
		return relievedLetter;
	}

	public void setRelievedLetter(byte[] relievedLetter) {
		this.relievedLetter = relievedLetter;
	}

}
