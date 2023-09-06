package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.OnboardingHRManager;

public interface IOnboardingHRManagerService {

	String createHRManager(OnboardingHRManager hrManager);

	List<OnboardingHRManager> getAllHRManager();

	OnboardingHRManager getHRManager(Integer id);

	String updateHRManager(OnboardingHRManager hrManager, Integer id);

	String deleteHRManager(Integer id);

	Long nextValue();

}
