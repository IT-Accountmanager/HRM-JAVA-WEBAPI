
package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IProfileRepository;
import com.hrm.main.services.IProfileService;

@Service
public class ProfileServiceImpl implements IProfileService {

	@Autowired
	IProfileRepository profileRepository;

	@Autowired
	IOnboardingRepository onboardingRepository;

}
