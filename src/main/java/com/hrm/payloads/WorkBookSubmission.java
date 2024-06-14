package com.hrm.payloads;

import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class WorkBookSubmission {
	private Long candidateId;
	private DetailsSubmissionStatus workBookSubmissionStatus;

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getWorkBookSubmissionStatus() {
		return workBookSubmissionStatus;
	}

	public void setWorkBookSubmissionStatus(DetailsSubmissionStatus workBookSubmissionStatus) {
		this.workBookSubmissionStatus = workBookSubmissionStatus;
	}

	public WorkBookSubmission() {
		super();
	}

}
