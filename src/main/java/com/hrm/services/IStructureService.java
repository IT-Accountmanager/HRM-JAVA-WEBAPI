package com.hrm.services;

import java.util.List;

import com.hrm.helper.EnumCollection.Departments;

public interface IStructureService {

	List<String> findEmployeesByDepartment(Departments department);

	// String findManagerByDepartment(Departments department, Sub_Department
	// subDepartment);

	/*
	 * List<String> findEmployeesBySubDepartment(Sub_Department subDepartment);
	 * 
	 * List<String> findManagerByDepartment(Departments department, Sub_Department
	 * subDepartment, String manager);
	 * 
	 * List<String> findEmployeesByDepartment(Departments department, Sub_Department
	 * subDepartment, String designation);
	 */
}
