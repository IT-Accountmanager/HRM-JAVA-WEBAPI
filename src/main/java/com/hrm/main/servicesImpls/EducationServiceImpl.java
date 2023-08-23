package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Education;
import com.hrm.main.repositories.IEducationRepository;
import com.hrm.main.services.IEducationService;
@Service
public class EducationServiceImpl implements IEducationService {

	@Autowired
	private IEducationRepository educationRepo;

	@Override
	public String createEducation(Education education) {
		try {
			var edu = this.educationRepo.save(education);
			if (edu.getId() > 0) {
				return "Education details are added : " + edu.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Education details are not added";

	}

	@Override
	public List<Education> getAllEducation() {
		List<Education> allEdu = educationRepo.findAll();
		return allEdu;
	}

}
