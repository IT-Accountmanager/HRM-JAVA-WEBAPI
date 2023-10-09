package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrm.main.models.OnboardingEmployeeSummary;

public interface IOnboardingEmployeeSummaryRepository extends JpaRepository<OnboardingEmployeeSummary, Integer> {

	@Query("SELECT department, employeeName, designation FROM OnboardingEmployeeSummary WHERE designation = 'Manager' GROUP BY department, employeeName, designation")
	List<String> findDepartments();

	/*
	 * SELECT d. department AS Department, dm.employee_name AS ManagerName,
	 * dm.designation AS ManagerDesignation, e.employee_name AS EmployeeName,
	 * e.designation AS EmployeeDesignation FROM onboarding_employee_summary e LEFT
	 * JOIN onboarding_employee_summary dm ON e.department= dm.department AND
	 * dm.designation='Manager'
	 * 
	 * LEFT JOIN (SELECT DISTINCT department, designation FROM
	 * onboarding_employee_summary WHERE designation = 'Manager') d ON e.department
	 * = d.department ORDER BY Department, ManagerName, EmployeeName;
	 */

	@Query("SELECT employeeName, designation FROM OnboardingEmployeeSummary WHERE department = 'MGMT' GROUP BY employeeName, designation")
	List<String> findMGMTEmployee();

	@Query("SELECT employeeName, designation FROM OnboardingEmployeeSummary WHERE department = 'HR' GROUP BY employeeName, designation")
	List<String> findHREmployee();

	@Query("SELECT employeeName, designation FROM OnboardingEmployeeSummary WHERE department = 'Finance' GROUP BY employeeName, designation")
	List<String> findFinanceEmployee();

	@Query("SELECT employeeName, designation FROM OnboardingEmployeeSummary WHERE department = 'Development' GROUP BY employeeName, designation")
	List<String> findDevelopmentEmployee();

}
