package com.hrm.main.servicesImpls;

import java.util.List;

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

	@Override
	public List<String> findEmployeesByDepartment(Departments department) {
		// Assuming Employee has a 'department' field and you want to find employees by
		// department
		List<Employee> employees = employeeRepository.findAllByDepartment(department);

		// Assuming you want to return a list of employee names as strings
		return employees.stream().map(Employee::getName) // Assuming there's a getEmployeeName() method in your
															// Employee class
				.toList(); // Requires Java 16 or later, otherwise, use Collectors.toList()
	}

}
