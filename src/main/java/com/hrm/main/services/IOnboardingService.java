package com.hrm.main.services;

import java.util.List;
import com.hrm.main.models.Onboarding;
import com.hrm.main.payloads.OnboardingDto;
import com.hrm.main.payloads.OnboardingEditDto;

public interface IOnboardingService {

	// String createOnboarding(Onboarding onboarding);

	Onboarding createOnboarding(Onboarding onboardingRequest);

	String createOnboarding(List<Onboarding> onboardings);

	List<OnboardingDto> getAllOnboarding();

	Onboarding getOnboardingByCandidateId(long candidateId);

	String updateOnboarding(OnboardingEditDto onboardingDto, long candidateId);

	String deleteOnboarding(Integer id);

	Long nextValue();

}
