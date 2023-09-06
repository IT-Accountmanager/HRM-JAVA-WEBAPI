package com.hrm.main.services;

import java.util.List;
import com.hrm.main.models.Onboarding;

public interface IOnboardingService {

	String createOnboarding(Onboarding onboarding);

	String createOnboarding(List<Onboarding> onboardings);

	List<Onboarding> getAllOnboarding();

	Onboarding getOnboardingById(int id);

	String updateOnboarding(Onboarding onboarding, Integer id);

	String deleteOnboarding(Integer id);

	Long nextValue();

}
