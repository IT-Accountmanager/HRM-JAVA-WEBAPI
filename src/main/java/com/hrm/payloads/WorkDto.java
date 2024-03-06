package com.hrm.payloads;

public class WorkDto {
	private String companyName;//comapanyName
	private String previousDesignation;//designation
	private float tenure;//tenure
	private long lastCTC;//ctc
	private String lastReportingManagerName;//contactName
	private long lastReportingManagerContactNo;//contactNo
	private String lastMonthPaySlip;//
	private String lastAppraisalLetter;//

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPreviousDesignation() {
		return previousDesignation;
	}

	public void setPreviousDesignation(String previousDesignation) {
		this.previousDesignation = previousDesignation;
	}

	public float getTenure() {
		return tenure;
	}

	public void setTenure(float tenure) {
		this.tenure = tenure;
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

	public String getLastMonthPaySlip() {
		return lastMonthPaySlip;
	}

	public void setLastMonthPaySlip(String lastMonthPaySlip) {
		this.lastMonthPaySlip = lastMonthPaySlip;
	}

	public String getLastAppraisalLetter() {
		return lastAppraisalLetter;
	}

	public void setLastAppraisalLetter(String lastAppraisalLetter) {
		this.lastAppraisalLetter = lastAppraisalLetter;
	}

}
