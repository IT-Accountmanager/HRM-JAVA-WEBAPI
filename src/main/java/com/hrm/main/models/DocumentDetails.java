package com.hrm.main.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class DocumentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docId;
	private String adharCardNo;
	private String panCardNo;
	private String drivingLicenseNo;
	private String passportNo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "docDetailsId")
	private List<DocumentUpload> docuUpload;

	public List<DocumentUpload> getDocuUpload() {
		return docuUpload;
	}

	public void setDocuUpload(List<DocumentUpload> docuUpload) {
		this.docuUpload = docuUpload;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getAdharCardNo() {
		return adharCardNo;
	}

	public void setAdharCardNo(String adharCardNo) {
		this.adharCardNo = adharCardNo;
	}

	public String getPanCardNo() {
		return panCardNo;
	}

	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public DocumentDetails(int docId, String adharCardNo, String panCardNo, String drivingLicenseNo, String passportNo,
			List<DocumentUpload> docuUpload) {
		super();
		this.docId = docId;
		this.adharCardNo = adharCardNo;
		this.panCardNo = panCardNo;
		this.drivingLicenseNo = drivingLicenseNo;
		this.passportNo = passportNo;
		this.docuUpload = docuUpload;
	}

	public DocumentDetails() {
		super();
	}

	@Override
	public String toString() {
		return "DocumentDetails [docId=" + docId + ", adharCardNo=" + adharCardNo + ", panCardNo=" + panCardNo
				+ ", drivingLicenseNo=" + drivingLicenseNo + ", passportNo=" + passportNo + ", docuUpload=" + docuUpload
				+ "]";
	}

}
