package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Document;
import com.hrm.main.models.Education;
import com.hrm.main.payloads.EducationStatusResponse;

public interface IEducationService {
	String createEducation(Education education, long candidateId);

	List<Education> getAllEducationByCandidateId(long candidateId);

	Education getEducation(Integer id);

	String deleteEducation(Integer id);

	String updateEducation(Education edu, Integer id);

	EducationStatusResponse getEducationStatusByCandidateId(long candidateId);

}
