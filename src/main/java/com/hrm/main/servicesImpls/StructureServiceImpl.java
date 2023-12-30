package com.hrm.main.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Employee;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Sub_Department;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.services.IStructureService;

@Service
public class StructureServiceImpl implements IStructureService {

	@Autowired
	IEmployeeRepository employeeRepository;

	public List<String> findEmployeesByDepartment(Departments department) {
		return employeeRepository.findAllByDepartment(department).stream().map(Employee::getName)
				.collect(Collectors.toList());
	}

	@Override
	public List<String> findEmployeesBySubDepartment(Sub_Department subDepartment) {
		return employeeRepository.findAllBySubDepartment(subDepartment).stream().map(Employee::getName)
				.collect(Collectors.toList());
	}

	/*
	 * @Override public List<String> findManagerByDepartment(Departments department,
	 * Sub_Department subDepartment) { return
	 * this.employeeRepository.findManagerByDepartmentAndSubDepartment(department,
	 * subDepartment).stream()
	 * .map(Employee::getAssignTo).collect(Collectors.toList()); }
	 */
	@Override
	public List<String> findManagerByDepartment(Departments department, Sub_Department subDepartment, String manager) {

		List<Employee> employeeList = this.employeeRepository.findByDepartmentAndSubDepartmentAndDesignation(department,
				subDepartment, manager);

		List<String> managerNames = new ArrayList<>();

		for (Employee employee : employeeList) {
			managerNames.add(employee.getName());
		}

		return managerNames;
	}

	@Override
	public List<String> findEmployeesByDepartment(Departments department, Sub_Department subDepartment,
			String designation) {
		List<Employee> employeeList = this.employeeRepository
				.findByDepartmentAndSubDepartmentAndDesignationNot(department, subDepartment, designation);

		List<String> employeeNames = new ArrayList<>();

		for (Employee employee : employeeList) {
			employeeNames.add(employee.getName());
		}

		return employeeNames;

	}

}
