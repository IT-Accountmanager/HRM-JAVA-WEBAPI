package com.hrm.payloads;

import com.hrm.helper.EnumCollection.CandidatesStatus;

public class CandidateStatusDto {

	private CandidatesStatus candidatesStatus;

	public CandidatesStatus getCandidatesStatus() {
		return candidatesStatus;
	}

	public void setCandidatesStatus(CandidatesStatus candidatesStatus) {
		this.candidatesStatus = candidatesStatus;
	}

}