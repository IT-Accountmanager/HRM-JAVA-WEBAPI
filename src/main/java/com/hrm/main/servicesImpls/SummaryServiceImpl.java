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
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.payloads.BasicInfoDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.SetManagerDto;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.payloads.WorkHistoryDto;
import com.hrm.main.payloads.WorkInfoDto;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.ISummaryService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SummaryServiceImpl implements ISummaryService {

	@Autowired
	IOnboardingRepository onboardingRepository;
	@Autowired
	IEmployeeRepository employeeRepository;
	@Autowired
	IWorkRepository workRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<SummaryDto> getAll() {

		// List<Employee> findAll =
		// this.employeeRepository.findByEmployeeStatus(EmployeeStatus.Active);

		List<Employee> findAll = this.employeeRepository.findAll();

		List<SummaryDto> summaryDtoList = new ArrayList<>();

		for (Employee employee : findAll) {

			SummaryDto summaryDto = new SummaryDto();

			summaryDto.setEmployeeId(employee.getEmployeeId());
			summaryDto.setName(employee.getName());
			summaryDto.setContactNumber(employee.getContactNumber());
			summaryDto.setEmailId(employee.getEmailId());
			summaryDto.setDateOfJoining(employee.getDateOfJoining());
			// summaryDto.setBondBreakAmount(employee.getBondBreakAmount());

			/*
			 * summaryDto.setDesignation(
			 * this.onboardingRepository.findByCandidateId(employee.getCandidateId()).
			 * getJobTitle());
			 */
			long candiId = employee.getCandidateId();
			summaryDto.setDesignation(this.onboardingRepository.findByCandidateId(candiId).getJobTitle());
			summaryDto.setDepartment(employee.getDepartment());
			summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
			summaryDto.setRelevantExperience(employee.getRelevantExperience());
			summaryDto.setAssignTo(employee.getAssignTo());
			summaryDto.setWorkLocation(employee.getWorkLocation());
			summaryDto.setAppointmentLetter(employee.getAppointmentLetter());

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

		System.out.println("___________________________________________________");
		System.out.println(employee.getCandidateId());
		System.out.println("___________________________________________________");

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
				employee.setWorkLocation(singleEmployee.getWorkLocation());

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

	@Override
	public List<EmployeesNameDto> getListOfEmployeesBySearchTerm(String searchTerm) {
		List<Employee> employees = this.employeeRepository.findByNameContainingIgnoreCase(searchTerm);

		// Mapping Employee entities to EmployeesNameDto
		return employees.stream().map(employee -> modelMapper.map(employee, EmployeesNameDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public SetManagerDto setManager(SetManagerDto assignTo, String employeeId) {

		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		modelMapper.map(assignTo, employee);

		this.employeeRepository.save(employee);

		return assignTo;
	}

	@Override
	public String addBasicInfo(BasicInfoDto basicInfo, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		modelMapper.map(basicInfo, employee);

		this.employeeRepository.save(employee);

		return " Basic Info of Employee Id : " + employeeId + " is added Successfully !";
	}

	@Override
	public String addWorkInfo(WorkInfoDto workInfo, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		modelMapper.map(workInfo, employee);

		this.employeeRepository.save(employee);

		return " Work Info of Employee Id : " + employeeId + " is added Successfully !";

	}

	@Override
	public String addWorkHistory(WorkHistoryDto workHistory, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		modelMapper.map(workHistory, employee);

		this.employeeRepository.save(employee);

		return " Work History of Employee Id : " + employeeId + " is added Successfully !";
	}

	@Override
	public String addResignationInfo(ResignationInfoDto resignationInfo, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		modelMapper.map(resignationInfo, employee);

		this.employeeRepository.save(employee);

		return " Resignation Info of Employee Id : " + employeeId + " is added Successfully !";
	}

	@Override
	public WorkInfoDto getWorkInfo(String employeeId) {
		try {
			Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {

				WorkInfoDto workInfo = new WorkInfoDto();

				workInfo.setAssignTo(employee.getAssignTo());
				workInfo.setDepartment(employee.getDepartment());
				workInfo.setDesignation(employee.getDesignation());
				workInfo.setJobTitle(employee.getJobTitle());
				workInfo.setSubDepartment(employee.getSubDepartment());

				return workInfo;

			} else {
				long candidateId = employee.getCandidateId();
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

				WorkInfoDto workInfo = new WorkInfoDto();

				workInfo.setJobTitle(onboarding.getJobTitle());

				return workInfo;
			}
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public BasicInfoDto getBasicInfo(String employeeId) {
		try {
			Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

			if (employee != null) {

				BasicInfoDto basicInfo = new BasicInfoDto();

				basicInfo.setDateOfJoining(employee.getDateOfJoining());
				basicInfo.setWorkLocation(employee.getWorkLocation());
				basicInfo.setEmployeeId(employee.getEmployeeId());
				basicInfo.setEmployeeStatus(employee.getEmployeeStatus());
				basicInfo.setEmployeeType(employee.getEmployeeType());
				basicInfo.setProbationPeriod(employee.getProbationPeriod());
				basicInfo.setWorkExperience(employee.getWorkExperience());

				return basicInfo;
			} else {
				long candidateId = employee.getCandidateId();
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

				BasicInfoDto basicInfo = new BasicInfoDto();

				basicInfo.setDateOfJoining(onboarding.getDateOfJoining());
				basicInfo.setWorkLocation(onboarding.getWorkLocation());

				return basicInfo;
			}
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public WorkHistoryDto getWorkHistory(String employeeId) {
		try {
			Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {
				WorkHistoryDto workHistory = new WorkHistoryDto();
				workHistory.setPreviouDepartment(employee.getPreviouDepartment());
				workHistory.setPreviouDesignation(employee.getPreviouDesignation());
				workHistory.setPreviouWorkFrom(employee.getPreviouWorkFrom());
				workHistory.setPreviouWorkUpto(employee.getPreviouWorkUpto());

				return workHistory;

			} else {
				long candidateId = employee.getCandidateId();
				Work work = this.workRepository.findByCandidateId(candidateId);

				return modelMapper.map(work, WorkHistoryDto.class);
			}
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public ResignationInfoDto getResignationInfo(String employeeId) {

		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

		return modelMapper.map(employee, ResignationInfoDto.class);
	}

	@Override
	public String changeEmployeeStatus(String employeeId, EmployeeGenerateDto status) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		employee.setEmployeeStatus(status.getEmployeeStatus());
		this.employeeRepository.save(employee);
		return "Status has been changed of Candidate Id : " + employeeId;
	}

}
