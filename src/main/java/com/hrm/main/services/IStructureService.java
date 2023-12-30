package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Employee;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Sub_Department;

public interface IStructureService {

	List<String> findEmployeesByDepartment(Departments department);

	// String findManagerByDepartment(Departments department, Sub_Department
	// subDepartment);

	List<String> findEmployeesBySubDepartment(Sub_Department subDepartment);

	List<String> findManagerByDepartment(Departments department, Sub_Department subDepartment, String manager);

	List<String> findEmployeesByDepartment(Departments department, Sub_Department subDepartment, String designation);

}
