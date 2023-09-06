package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrm.main.models.OnboardingEmployeeSummary;

public interface IOnboardingEmployeeSummaryRepository extends JpaRepository<OnboardingEmployeeSummary, Integer> {

	@Query("SELECT DISTINCT department FROM OnboardingEmployeeSummary")
	List<String> findDepartments();
}
