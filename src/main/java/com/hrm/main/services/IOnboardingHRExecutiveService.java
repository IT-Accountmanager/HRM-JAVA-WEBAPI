package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.OnboardingHRExecutive;

public interface IOnboardingHRExecutiveService {
	
	String createExecutive(OnboardingHRExecutive hrExecutive);	
	
	List<OnboardingHRExecutive> getAllExecutive();
	
	OnboardingHRExecutive getExecutiveById(int id);
	
	String updateHRExecutive(OnboardingHRExecutive hrExecutive,Integer id);
	
	String deleteHrExecutive(Integer id);

}
