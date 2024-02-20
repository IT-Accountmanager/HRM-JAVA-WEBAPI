package com.hrm.main.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class BankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_id_seq")
	@SequenceGenerator(name = "bank_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "bank_seq")
	@Column(name = "id")
	private int bankDetId;
	private String accountNo;
	private String bankHolderName;
	private String bankName;
	private String ifscCode;
	private String branchName;
	private String branchCity;
	@Lob
	// @Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] bankDoc;
	@Transient
	public String base64Data;

	public int getBankDetId() {
		return bankDetId;
	}

	public void setBankDetId(int bankDetId) {
		this.bankDetId = bankDetId;
	}

	public String getBankHolderName() {
		return bankHolderName;
	}

	public void setBankHolderName(String bankHolderName) {
		this.bankHolderName = bankHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public byte[] getBankDoc() {
		return bankDoc;
	}

	public void setBankDoc(byte[] bankDoc) {
		this.bankDoc = bankDoc;
	}

}
