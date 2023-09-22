
package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Profile;

public interface IProfileService {

	/* String changeStatus(Integer id, String status); */

	List<Onboarding> getOnboardings();

	Onboarding getOnboardingById(Integer id);

	int createProfile(Profile profile);

	int createPersonalDetails(PersonalDetails personalDetails);
}
