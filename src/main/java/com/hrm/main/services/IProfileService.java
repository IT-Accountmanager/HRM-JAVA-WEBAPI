
package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Profile;

public interface IProfileService {

	/* String changeStatus(Integer id, String status); */

	List<Onboarding> getOnboardings();

	Onboarding getOnboardingById(Integer id);

	String createProfile(Profile profile);
}
