
package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Profile;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IProfileRepository;
import com.hrm.main.services.IProfileService;

@Service
public class ProfileServiceImpl implements IProfileService {

	@Autowired
	IProfileRepository profileRepository;

	@Autowired
	IOnboardingRepository onboardingRepository;

	@Override
	public List<Onboarding> getOnboardings() {
		return this.onboardingRepository.findAll();

	}

	@Override
	public Onboarding getOnboardingById(Integer id) {
		return this.onboardingRepository.findById(id).get();
	}

	@Override
	public int createProfile(Profile profile) {
		Profile save = this.profileRepository.save(profile);
		return save.getProfileId();
	}

	@Override
	public int createPersonalDetails(PersonalDetails personalDetails) {
		
		return 0;
	}

}
