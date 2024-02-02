package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;

public class CandidateStatusDto {

	private CandidatesStatus candidatesStatus;

	public CandidatesStatus getCandidatesStatus() {
		return candidatesStatus;
	}

	public void setCandidatesStatus(CandidatesStatus candidatesStatus) {
		this.candidatesStatus = candidatesStatus;
	}

}
