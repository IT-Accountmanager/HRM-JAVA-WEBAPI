package com.hrm.services;

import java.util.List;

import com.hrm.models.Document;
import com.hrm.models.Education;
import com.hrm.payloads.EducationStatusResponse;

public interface IEducationService {
	String createEducation(Education education, long candidateId);

	List<Education> getAllEducationByCandidateId(long candidateId);

	Education getEducation(Integer id);

	String deleteEducation(Integer id);

	String updateEducation(Education edu, Integer id);

	public EducationStatusResponse getEducationStatusByCandidateId(long candidateId);

}
