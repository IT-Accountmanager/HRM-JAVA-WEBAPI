package com.hrm.models;

import com.hrm.Helper.EnumCollection.ApprovalStatus;
import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal")
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_seq")
	@SequenceGenerator(name = "personal_seq", initialValue = 1, allocationSize = 1, sequenceName = "personal_seq")
	@Column(name = "pId")
	private int personalId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pdid")
	private PersonalDetails personalDetails;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adId")
	private AddressDetails addressDetails;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "docId")
	private DocumentDetails documentDetails;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bankDetId")
	private BankDetails bankDetails;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "socialDetailsId")
	private SocialDetails socialDetails;
	@Column(name = "candidate_id")
	private long candidateId;
	private DetailsSubmissionStatus personalSubmissionStatus = DetailsSubmissionStatus.Pending;
	private ApprovalStatus hrExecutiveApprovalStatus = ApprovalStatus.Pending;
	private String hrExecutiveRemark;
	private ApprovalStatus hrManagerApprovalStatus = ApprovalStatus.Pending;

	private String hrManagerRemark;

	public Personal() {
		super();
	}

	public int getPersonalId() {
		return personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public AddressDetails getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}

	public DocumentDetails getDocumentDetails() {
		return documentDetails;
	}

	public void setDocumentDetails(DocumentDetails documentDetails) {
		this.documentDetails = documentDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public SocialDetails getSocialDetails() {
		return socialDetails;
	}

	public void setSocialDetails(SocialDetails socialDetails) {
		this.socialDetails = socialDetails;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getPersonalSubmissionStatus() {
		return personalSubmissionStatus;
	}

	public void setPersonalSubmissionStatus(DetailsSubmissionStatus personalSubmissionStatus) {
		this.personalSubmissionStatus = personalSubmissionStatus;
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

	public void updateFrom(Personal newPersonal) {
		if (newPersonal.getPersonalDetails() != null) {
			this.setPersonalDetails(newPersonal.getPersonalDetails());
		}
		if (newPersonal.getAddressDetails() != null) {
			this.setAddressDetails(newPersonal.getAddressDetails());
		}
		if (newPersonal.getDocumentDetails() != null) {
			this.setDocumentDetails(newPersonal.getDocumentDetails());
		}
		if (newPersonal.getBankDetails() != null) {
			this.setBankDetails(newPersonal.getBankDetails());
		}
		if (newPersonal.getSocialDetails() != null) {
			this.setSocialDetails(newPersonal.getSocialDetails());
		}
	}
}
