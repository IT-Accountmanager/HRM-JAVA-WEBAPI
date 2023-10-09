package com.hrm.main.services;

import com.hrm.main.models.Personal;
import com.hrm.main.models.PersonalDetails;

public interface IPersonalService {

	String addPersonal(Personal personal, Integer candidateId);

	Personal getPersonalDetailsByCandidateId(Integer candidateId);

	Personal getPersonalById(Integer id);

}
