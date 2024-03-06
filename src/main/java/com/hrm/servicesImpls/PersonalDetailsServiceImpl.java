package com.hrm.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.PersonalDetails;
import com.hrm.repositories.IOnboardingRepository;
import com.hrm.repositories.IPersonalDetailsRepository;
import com.hrm.services.IPersonalDetailsService;

@Service
public class PersonalDetailsServiceImpl implements IPersonalDetailsService {

	@Autowired
	IPersonalDetailsRepository personalDetailsRepo;

	@Autowired
	IOnboardingRepository onboardingRepository;

	@Override
	public String addPersonalDetails(PersonalDetails personalDetails) {

		personalDetailsRepo.save(personalDetails);

		return "Personal Details Added Successfully";
	}

}
