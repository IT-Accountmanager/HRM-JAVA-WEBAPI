package com.hrm.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.models.Employee;
import com.hrm.models.Onboarding;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	boolean existsByCandidateId(long candidateId);

	List<Employee> findAllByDepartment(Departments department);

	Employee findByCandidateId(long candidateId);

	boolean existsByEmailId(String emailId);

	boolean existsByContactNumber(long contactNumber);

	List<Employee> findByNameContainingIgnoreCase(String searchTerm);

	Employee findByEmployeeId(String employeeId);

	boolean existsByEmailIdOrContactNumber(String emailId, long contactNumber);

	/*
	 * List<Employee> findAllBySubDepartment(Sub_Department subDepartment);
	 */

	// List<Employee> findAllByDepartmentAndSubDepartment(Departments department,
	// Sub_Department subDepartment);

	/*
	 * List<Employee> findByDepartmentAndSubDepartmentAndDesignation(Departments
	 * department, Sub_Department subDepartment, String designation);
	 */

	/*
	 * List<Employee> findByDepartmentAndSubDepartmentAndDesignationNot(Departments
	 * department, Sub_Department subDepartment, String designation);
	 */

	List<Employee> findByEmployeeStatus(EmployeeStatus employeeStatus);

	List<Employee> findAllByManager(String manager);


}