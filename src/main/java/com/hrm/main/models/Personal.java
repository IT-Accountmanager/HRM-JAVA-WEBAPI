package com.hrm.main.models;

import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
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
	private String candidateId;

	private DetailsSubmissionStatus personalSubmissionStatus = DetailsSubmissionStatus.pending;

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

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getPersonalSubmissionStatus() {
		return personalSubmissionStatus;
	}

	public void setPersonalSubmissionStatus(DetailsSubmissionStatus personalSubmissionStatus) {
		this.personalSubmissionStatus = personalSubmissionStatus;
	}

	public Personal(int personalId, PersonalDetails personalDetails, AddressDetails addressDetails,
			DocumentDetails documentDetails, BankDetails bankDetails, SocialDetails socialDetails, String candidateId) {
		super();
		this.personalId = personalId;
		this.personalDetails = personalDetails;
		this.addressDetails = addressDetails;
		this.documentDetails = documentDetails;
		this.bankDetails = bankDetails;
		this.socialDetails = socialDetails;
		this.candidateId = candidateId;
	}

	public Personal() {
		super();
	}

}
