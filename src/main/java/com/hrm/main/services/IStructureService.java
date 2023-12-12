package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Employee;
import com.hrm.main.models.Helper.EnumCollection.Departments;

public interface IStructureService {

	List<String> findEmployeesByDepartment(Departments department);

}
