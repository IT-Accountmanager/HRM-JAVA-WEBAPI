package com.hrm.services;

import com.hrm.models.Agreement;
import com.hrm.payloads.AgreementDto;
import com.hrm.payloads.AgreementEditDto;
import com.hrm.payloads.AgreementStatusResponse;

public interface IAgreementService {

	String add(long candidateId, Agreement agreement);

	Agreement getByCandidateId(long candidateId);

	AgreementStatusResponse getStatusByCandidateId(long candidateId);

	AgreementDto getPreAgreementInfo(long candidateId);

	String editAgreement(AgreementEditDto agreement, long candidateId);

	/*
	 * String delete(String employeeId);
	 */
}
