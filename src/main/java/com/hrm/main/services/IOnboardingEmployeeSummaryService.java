package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.OnboardingEmployeeSummary;

public interface IOnboardingEmployeeSummaryService {

	String createSummary(OnboardingEmployeeSummary summary);

	List<OnboardingEmployeeSummary> getAllSummary();

	OnboardingEmployeeSummary getSummaryById(int id);

	// String updateSummary(OnboardingEmployeeSummary summary,Integer id);

	// String deleteSummary(Integer id);

	List<String> findDepartments();

}
