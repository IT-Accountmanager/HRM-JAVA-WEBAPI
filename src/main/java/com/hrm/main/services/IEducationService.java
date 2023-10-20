package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Document;
import com.hrm.main.models.Education;
import com.hrm.main.payloads.EducationStatusResponse;

public interface IEducationService {
	String createEducation(Education education, String candidateId);

	List<Education> getAllEducation();

	Education getEducation(Integer id);

	String deleteEducation(Integer id);

	String updateEducation(Education edu, Integer id);

	EducationStatusResponse getEducationStatusByCandidateId(String candidateId);

}
