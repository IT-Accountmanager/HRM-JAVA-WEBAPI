package com.hrm.main.services;

import com.hrm.main.models.Agreement;
import com.hrm.main.payloads.AgreementDto;
import com.hrm.main.payloads.AgreementStatusResponse;

public interface IAgreementService {

	String add(long candidateId, Agreement agreement);

	Agreement getByCandidateId(long candidateId);

	AgreementStatusResponse getStatusByCandidateId(long candidateId);

	AgreementDto getPreAgreementInfo(long candidateId);

	/*
	 * String delete(String employeeId);
	 */
}
