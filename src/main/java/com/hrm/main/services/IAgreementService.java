package com.hrm.main.services;

import com.hrm.main.models.Agreement;
import com.hrm.main.payloads.AgreementDto;

public interface IAgreementService {

	String add(long candidateId, Agreement agreement);

	AgreementDto getByCandidateId(long candidateId);

	/*
	 * String delete(String employeeId);
	 */
}
