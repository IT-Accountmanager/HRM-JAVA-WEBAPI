package com.hrm.main.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Employee;
import com.hrm.main.models.Helper.EnumCollection.Departments;
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
	public List<String> findManagerByDepartment(Departments department) {
		return this.employeeRepository.findAllByDepartment(department).stream().map(Employee::getAssignTo)
				.collect(Collectors.toList());
	}

}
