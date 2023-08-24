package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Document;
import com.hrm.main.models.Education;

public interface IEducationService {
	String createEducation(Education education);

	List<Education> getAllEducation();

	Education getEducation(Integer id);

	String deleteEducation(Integer id);

	String updateEducation(Education edu, Integer id);

}
