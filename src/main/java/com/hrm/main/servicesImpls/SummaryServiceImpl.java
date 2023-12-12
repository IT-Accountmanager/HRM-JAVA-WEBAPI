package com.hrm.main.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrm.main.models.Employee;
import com.hrm.main.models.Onboarding;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.services.ISummaryService;

@Service
public class SummaryServiceImpl implements ISummaryService {

	@Autowired
	IOnboardingRepository onboardingRepository;
	@Autowired
	IEmployeeRepository employeeRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<SummaryDto> getAll() {

		List<Employee> findAll = this.employeeRepository.findAll();
		List<SummaryDto> summaryDtoList = new ArrayList<>();

		for (Employee employee : findAll) {

			SummaryDto summaryDto = new SummaryDto();

			summaryDto.setEmployeeId(employee.getEmployeeId());
			summaryDto.setName(employee.getName());
			summaryDto.setContactNumber(employee.getContactNumber());
			summaryDto.setEmailId(employee.getEmailId());
			summaryDto.setDateOfJoining(employee.getDateOfJoining());
			summaryDto.setBondBreakAmount(employee.getBondBreakAmount());
			summaryDto.setDesignation(employee.getDesignation());
			summaryDto.setDepartment(employee.getDepartment());
			summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
			summaryDto.setRelevantExperience(employee.getRelevantExperience());

			summaryDtoList.add(summaryDto);

		}

		return summaryDtoList;
	}

	@Override
	public EmployeeViewDto getSummaryByCandidateId(long candidateId) {
		Employee employee = this.employeeRepository.findByCandidateId(candidateId);
		EmployeeViewDto employeeDto = new EmployeeViewDto();
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setName(employee.getName());
		employeeDto.setContactNumber(employee.getContactNumber());
		employeeDto.setEmailId(employee.getEmailId());
		employeeDto.setDateOfJoining(employee.getDateOfJoining());
		employeeDto.setDesignation(employee.getDesignation());
		employeeDto.setDepartment(employee.getDepartment());
		employeeDto.setEmployeeStatus(employee.getEmployeeStatus());
		employeeDto.setRelevantExperience(employee.getRelevantExperience());
		employeeDto.setAssignTo(employee.getAssignTo());

		return employeeDto;
	}

	@Transactional
	@Override
	public String importEmployees(List<EmployeeViewDto> employees) {
		try {
			if (employees == null || employees.isEmpty()) {
				return "No employees to import.";
			}

			for (EmployeeViewDto singleEmployee : employees) {
				if (employeeRepository.existsByEmailId(singleEmployee.getEmailId())) {
					continue;
				}

				if (employeeRepository.existsByContactNumber(singleEmployee.getContactNumber())) {
					continue;
				}
				Employee employee = new Employee();
				employee.setEmployeeId(singleEmployee.getEmployeeId());
				employee.setName(singleEmployee.getName());
				employee.setContactNumber(singleEmployee.getContactNumber());
				employee.setEmailId(singleEmployee.getEmailId());
				employee.setDateOfJoining(singleEmployee.getDateOfJoining());
				employee.setDesignation(singleEmployee.getDesignation());
				employee.setDepartment(singleEmployee.getDepartment());
				employee.setEmployeeStatus(singleEmployee.getEmployeeStatus());
				employee.setRelevantExperience(singleEmployee.getRelevantExperience());
				employee.setAssignTo(singleEmployee.getAssignTo());
				employee.setBondBreakAmount(singleEmployee.getBondBreakAmount());

				// Save the employee using your service/repository
				this.employeeRepository.save(employee);
			}
		} catch (Exception e) {
			// Handle exception, log it, or rethrow
			return "Error importing employees: " + e.getMessage();
		}

		return "Employees Imported Successfully";
	}

	@Override
	public List<EmployeesNameDto> getListOfEmployees() {
		List<Employee> employees = this.employeeRepository.findAll();

		List<EmployeesNameDto> employeeDtoList = employees.stream()
				.map(employee -> modelMapper.map(employee, EmployeesNameDto.class)).collect(Collectors.toList());

		return employeeDtoList;
	}

}
