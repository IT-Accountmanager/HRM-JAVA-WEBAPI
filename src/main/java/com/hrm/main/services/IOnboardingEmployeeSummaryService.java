package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.OnboardingEmployeeSummary;

public interface IOnboardingEmployeeSummaryService {

	String createSummary(OnboardingEmployeeSummary summary);

	List<OnboardingEmployeeSummary> getAllSummary();

	// String updateSummary(OnboardingEmployeeSummary summary,Integer id);

	// String deleteSummary(Integer id);

	List<String> findDepartments();

	List<String> findMGMTEmployee();

	List<String> findFinanceEmployee();

	List<String> findDevelopmentEmployee();

	List<String> findHREmployee();

	List<String> findDFSEmployee();

	List<String> findITEmployee();

	List<String> findBIMEmployee();

	List<String> findEDSEmployee();

	List<String> findSystemAdminEmployee();

	List<String> findSalesEmployee();

	List<String> findIASEmployee();

}
