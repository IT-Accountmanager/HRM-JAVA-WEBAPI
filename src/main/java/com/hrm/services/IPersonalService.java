package com.hrm.services;

import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.models.Personal;
import com.hrm.models.PersonalDetails;
import com.hrm.payloads.PersonalStatusResponse;

public interface IPersonalService {

	String addPersonal(Personal personal, long candidateId);

	Personal getPersonalDetailsByCandidateId(long candidateId);

	Personal getPersonalById(Integer id);

	PersonalStatusResponse getStatusByCandidateId(long candidateId);

}
