package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Helper.EnumCollection.Departments;

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
