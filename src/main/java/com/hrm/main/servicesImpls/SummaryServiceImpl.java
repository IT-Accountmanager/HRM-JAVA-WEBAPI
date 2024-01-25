package com.hrm.main.servicesImpls;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hrm.main.models.Employee;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.payloads.BasicInfoDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.SetManagerDto;
import com.hrm.main.payloads.SummaryAddressInfoDto;
import com.hrm.main.payloads.SummaryContactInfoDto;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.payloads.SummaryPersonalInfoDto;
import com.hrm.main.payloads.WorkHistoryDto;
import com.hrm.main.payloads.WorkInfoDto;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
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
	IPersonalRepository personalRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<SummaryDto> getAll() {

		// List<Employee> findAll =
		// this.employeeRepository.findByEmployeeStatus(EmployeeStatus.Active);

		List<Employee> findAll = this.employeeRepository.findAll();

		List<SummaryDto> summaryDtoList = new ArrayList<>();

		for (Employee employee : findAll) {

			Onboarding candidate = this.onboardingRepository.findByCandidateId(employee.getCandidateId());
			Personal details = this.personalRepository.findByCandidateId(employee.getCandidateId());
			SummaryDto summaryDto = new SummaryDto();

			summaryDto.setCandidateId(employee.getCandidateId());
			summaryDto.setEmployeeId(employee.getEmployeeId());
			summaryDto.setName(employee.getName());
			summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
			summaryDto.setContactNumber(employee.getContactNumber());
			summaryDto.setEmailId(employee.getEmailId());
			summaryDto.setDateOfJoining(employee.getDateOfJoining());
			summaryDto.setDepartment(employee.getDepartment());
			// summaryDto.setSubDepartment(employee.getSubDepartment);
			summaryDto.setAssignTo(employee.getAssignTo());
			summaryDto.setDesignation(employee.getDesignation());
			// summaryDto.setTotalExperience();
			summaryDto.setJoinedCtc(candidate.getCtc());
			// summaryDto.setCurrentCtc();
			summaryDto.setServiceCommitment(candidate.getServiceCommitment());
			// summaryDto.setNumberOfWorkingDays();
			// summaryDto.setNextApprisalQuater();
			summaryDto.setDateOfBirth(details.getPersonalDetails().getDateOfBirth());
			summaryDto.setBloodGroup(details.getPersonalDetails().getBloodGroup());
			summaryDto.setFatherName(details.getPersonalDetails().getFathersName());
			// summaryDto.setEmergencyContact();
			summaryDto.setPermanentAddress((details.getAddressDetails().getPermanentAdd().getHouseNo()) + ", "
					+ (details.getAddressDetails().getPermanentAdd().getArea()) + ", near "
					+ (details.getAddressDetails().getPermanentAdd().getLandmark()) + ", "
					+ (details.getAddressDetails().getPermanentAdd().getCity()) + ", "
					+ (details.getAddressDetails().getPermanentAdd().getState()) + ", "
					+ (details.getAddressDetails().getPermanentAdd().getPincode()));
			summaryDto.setTemporaryAddress((details.getAddressDetails().getPresentAdd().getHouseNo()) + ", "
					+ (details.getAddressDetails().getPresentAdd().getArea()) + ", near "
					+ (details.getAddressDetails().getPresentAdd().getLandmark()) + ", "
					+ (details.getAddressDetails().getPresentAdd().getCity()) + ", "
					+ (details.getAddressDetails().getPresentAdd().getState()) + ", "
					+ (details.getAddressDetails().getPresentAdd().getPincode()));
			summaryDto.setAadharCardNumber(details.getDocumentDetails().getAdharCardNo());
			summaryDto.setPanCardNumber(details.getDocumentDetails().getPanCardNo());
			// summaryDto.setUanNumber();
			summaryDto.setBankAccountNumber(details.getBankDetails().getAccountNo());
			// summaryDto.setQualification();
			// summaryDto.setSpecialization();
			// summaryDto.setYearOfPassout();
			// summaryDto.setResignationDate();
			// summaryDto.setActualLastWorkingDay();
			summaryDto.setRelevantExperience(employee.getRelevantExperience());
			summaryDto.setWorkLocation(employee.getWorkLocation());

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
				// workInfo.setSubDepartment(employee.getSubDepartment());
				workInfo.setWorkLocation(employee.getWorkLocation());

				return workInfo;

			} else {
				long candidateId = employee.getCandidateId();
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

				WorkInfoDto workInfo = new WorkInfoDto();

				workInfo.setDesignation(onboarding.getJobTitleDesignation());
				workInfo.setWorkLocation(onboarding.getWorkLocation());

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
				Personal details = this.personalRepository.findByCandidateId(employee.getCandidateId());

				basicInfo.setEmployeeStatus(employee.getEmployeeStatus());
				long workingDays = calculateWorkingDays(employee.getDateOfJoining(), LocalDate.now());
				basicInfo.setNumberOfWorkingDays(workingDays);
				basicInfo.setProbationPeriod(employee.getProbationPeriod());
				basicInfo.setWorkLocation(employee.getWorkLocation());
				basicInfo.setCurrentCtc(employee.getCurrentCtc());
				basicInfo.setNextApprisalQuater(employee.getNextApprisalQuater());
				basicInfo.setContactNumber(employee.getContactNumber());
				// basicInfo.setEmergencyContact(employee.ge);
				// basicInfo.setBankAccountNumber(employee.);
				basicInfo.setPermanentAddress((details.getAddressDetails().getPermanentAdd().getHouseNo()) + ", "
						+ (details.getAddressDetails().getPermanentAdd().getArea()) + ", near "
						+ (details.getAddressDetails().getPermanentAdd().getLandmark()) + ", "
						+ (details.getAddressDetails().getPermanentAdd().getCity()) + ", "
						+ (details.getAddressDetails().getPermanentAdd().getState()) + ", "
						+ (details.getAddressDetails().getPermanentAdd().getPincode()));
				basicInfo.setTemporaryAddress((details.getAddressDetails().getPresentAdd().getHouseNo()) + ", "
						+ (details.getAddressDetails().getPresentAdd().getArea()) + ", near "
						+ (details.getAddressDetails().getPresentAdd().getLandmark()) + ", "
						+ (details.getAddressDetails().getPresentAdd().getCity()) + ", "
						+ (details.getAddressDetails().getPresentAdd().getState()) + ", "
						+ (details.getAddressDetails().getPresentAdd().getPincode()));
				return basicInfo;
			} else {

				BasicInfoDto basicInfo = new BasicInfoDto();

				return basicInfo;
			}
		} catch (Exception e) {
			return null;

		}
	}

	private long calculateWorkingDays(LocalDate startDate, LocalDate endDate) {
		long workingDays = 0;
		LocalDate currentDate = startDate;

		while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
			// Assuming weekends are Saturday and Sunday
			if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
				workingDays++;
			}
			currentDate = currentDate.plusDays(1);
		}

		return workingDays;
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

	@Override
	public SummaryPersonalInfoDto getPersonalInfo(String employeeId) {
		try {
			long candidateId = this.employeeRepository.findByEmployeeId(employeeId).getCandidateId();

			Personal personal = this.personalRepository.findByCandidateId(candidateId);

			if (personal != null && personal.getPersonalDetails() != null) {
				SummaryPersonalInfoDto info = new SummaryPersonalInfoDto();

				info.setDateOfBirth(personal.getPersonalDetails().getDateOfBirth());
				info.setBloodGroup(personal.getPersonalDetails().getBloodGroup());
				info.setGender(personal.getPersonalDetails().getGender());
				info.setMaritalStatus(personal.getPersonalDetails().getMaritalStatus());

				return info;
			} else {

				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public SummaryContactInfoDto getContactInfo(String employeeId) {
		try {
			long candidateId = this.employeeRepository.findByEmployeeId(employeeId).getCandidateId();

			Personal personal = this.personalRepository.findByCandidateId(candidateId);

			if (personal != null && personal.getPersonalDetails() != null) {
				SummaryContactInfoDto info = new SummaryContactInfoDto();

				info.setAlternativeNumber(personal.getPersonalDetails().getAlternativePhoneNo());
				info.setContactNumber(personal.getPersonalDetails().getPhoneNo());
				// info.setOfficialMailId();
				info.setPersonalMailId(personal.getPersonalDetails().getPersonalMailId());

				return info;
			} else {

				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public SummaryAddressInfoDto getAddressInfo(String employeeId) {
		try {
			long candidateId = this.employeeRepository.findByEmployeeId(employeeId).getCandidateId();

			Personal personal = this.personalRepository.findByCandidateId(candidateId);

			if (personal != null && personal.getPersonalDetails() != null) {
				SummaryAddressInfoDto info = new SummaryAddressInfoDto();

				info.setPermanentAddress(personal.getAddressDetails().getPermanentAdd().getHouseNo() + ", "
						+ personal.getAddressDetails().getPermanentAdd().getArea() + ", near "
						+ personal.getAddressDetails().getPermanentAdd().getLandmark() + ", "
						+ personal.getAddressDetails().getPermanentAdd().getCity() + ", "
						+ personal.getAddressDetails().getPermanentAdd().getState());
				info.setPermanentAddressSince(null);
				info.setPermanentHouseType(null);
				info.setPermanentPincode(personal.getAddressDetails().getPermanentAdd().getPincode());
				info.setPresentAddress(personal.getAddressDetails().getPresentAdd().getHouseNo() + ", "
						+ personal.getAddressDetails().getPresentAdd().getArea() + ", near "
						+ personal.getAddressDetails().getPresentAdd().getLandmark() + ", "
						+ personal.getAddressDetails().getPresentAdd().getCity() + ", "
						+ personal.getAddressDetails().getPresentAdd().getState());
				info.setPresentAddressSince(null);
				info.setPresentHouseType(null);
				info.setPresentPincode(personal.getAddressDetails().getPresentAdd().getPincode());

				return info;
			} else {

				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

}
