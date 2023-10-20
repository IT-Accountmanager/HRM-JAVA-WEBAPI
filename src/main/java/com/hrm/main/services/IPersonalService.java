package com.hrm.main.services;

import com.hrm.main.models.Personal;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.main.payloads.PersonalStatusResponse;

public interface IPersonalService {

	String addPersonal(Personal personal, String candidateId);

	Personal getPersonalDetailsByCandidateId(String candidateId);

	Personal getPersonalById(Integer id);

	PersonalStatusResponse getStatusByCandidateId(String candidateId);

}
