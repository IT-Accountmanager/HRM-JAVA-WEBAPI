package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.PersonalDetails;
import com.hrm.main.repositories.IPersonalDetailsRepository;
import com.hrm.main.services.IPersonalDetailsService;

@Service
public class PersonalDetailsServiceImpl implements IPersonalDetailsService {

	@Autowired
	IPersonalDetailsRepository personalDetailsRepo;

	@Override
	public String addPersonalDetails(PersonalDetails personalDetails) {
		PersonalDetails details = personalDetailsRepo.save(personalDetails);
		return "Personal Details Added Successfully";
	}

}
