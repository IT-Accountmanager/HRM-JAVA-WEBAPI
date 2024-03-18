package com.hrm.servicesImpls;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.models.BankDetails;
import com.hrm.models.DocumentDetails;
import com.hrm.models.Education;
import com.hrm.models.Employee;
import com.hrm.models.Onboarding;
import com.hrm.models.Personal;
import com.hrm.models.PersonalDetails;
import com.hrm.payloads.BasicInfoDto;
import com.hrm.payloads.EmployeeGenerateDto;
import com.hrm.payloads.EmployeeViewDto;
import com.hrm.payloads.EmployeesNameDto;
import com.hrm.payloads.SetManagerDto;
import com.hrm.payloads.SummaryAddressInfoDto;
import com.hrm.payloads.SummaryContactInfoDto;
import com.hrm.payloads.SummaryDto;
import com.hrm.payloads.SummaryPersonalInfoDto;
import com.hrm.payloads.WorkHistoryDto;
import com.hrm.payloads.WorkInfoDto;
import com.hrm.repositories.IBankDetailsRepository;
import com.hrm.repositories.IDocumentsDetailsRepository;
import com.hrm.repositories.IEducationRepository;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.IOnboardingRepository;
import com.hrm.repositories.IPersonalDetailsRepository;
import com.hrm.repositories.IPersonalRepository;
import com.hrm.repositories.IWorkRepository;
import com.hrm.services.ISummaryService;

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
	IPersonalDetailsRepository personalDetailsRepository;
	@Autowired
	IDocumentsDetailsRepository documentsDetailsRepository;
	@Autowired
	IEducationRepository educationRepository;
	@Autowired
	IBankDetailsRepository bankDetailsRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public List<SummaryDto> getAll() {

		// List<Employee> findAll =
		// this.employeeRepository.findByEmployeeStatus(EmployeeStatus.Active);

		List<Employee> findAll = this.employeeRepository.findAll();

		List<SummaryDto> summaryDtoList = new ArrayList<>();

		for (Employee employee : findAll) {

			if (employee.isImported()) {
				SummaryDto summaryDto = new SummaryDto();

				long candidateId = employee.getCandidateId();

				Personal personal = this.personalRepository.findByCandidateId(candidateId);
				Education education = this.educationRepository.findByCandidateId(candidateId);
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

				summaryDto.setCandidateId(candidateId);
				summaryDto.setEmployeeId(employee.getEmployeeId());
				summaryDto.setName(employee.getName());
				summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
				summaryDto.setEmployeeCategory(employee.getEmployeeCategory());
				summaryDto.setContactNumber(employee.getContactNumber());
				summaryDto.setEmailId(employee.getEmailId());
				summaryDto.setDateOfJoining(employee.getDateOfJoining());
				summaryDto.setDepartment(employee.getDepartment());
				summaryDto.setSubDepartment(employee.getSubDepartment());
				summaryDto.setAssignTo(employee.getAssignTo());
				summaryDto.setDesignation(employee.getDesignation());
				summaryDto.setCategoryControl(employee.getCategoryControl());
				summaryDto.setTotalExperience(null);
				summaryDto.setJoinedCtc(employee.getJoinedCtc());
				summaryDto.setCurrentCtc(employee.getCurrentCtc());
				summaryDto.setServiceCommitment(onboarding.getServiceCommitment());
				summaryDto.setNumberOfWorkingDays(employee.getNumberOfWorkingDays());
				summaryDto.setNextApprisalQuater(employee.getNextApprisalQuater());
				summaryDto.setDateOfBirth(personal.getPersonalDetails().getDateOfBirth());
				summaryDto.setBloodGroup(personal.getPersonalDetails().getBloodGroup());
				summaryDto.setFatherName(personal.getPersonalDetails().getFathersName());
				summaryDto.setEmergencyContact(null);
				summaryDto.setPermanentAddress(null);
				summaryDto.setTemporaryAddress(null);
				summaryDto.setAadharCardNumber(personal.getDocumentDetails().getAdharCardNo());
				summaryDto.setPanCardNumber(personal.getDocumentDetails().getPanCardNo());
				summaryDto.setUanNumber(employee.getUanNumber());
				summaryDto.setBankAccountNumber(personal.getBankDetails().getAccountNo());
				summaryDto.setQualification(education.getQualification());
				summaryDto.setSpecialization(education.getStream());
				summaryDto.setYearOfPassout(0);
				summaryDto.setResignationDate(null);
				summaryDto.setActualLastWorkingDay(null);

				summaryDtoList.add(summaryDto);

			}

			System.out.println("___________________________________________________________________________");

			System.out.println("Employee Id : " + employee.getEmployeeId());
			System.out.println("");

			System.out.println("___________________________________________________________________________");

			if (!employee.isImported()) {

				System.out.println("______________________________________");
				System.out.println("Candidate Id : " + employee.getCandidateId());
				Onboarding candidate = this.onboardingRepository.findByCandidateId(employee.getCandidateId());
				Personal details = this.personalRepository.findByCandidateId(employee.getCandidateId());
				List<Education> educations = this.educationRepository.findAllByCandidateId(employee.getCandidateId());
				SummaryDto summaryDto = new SummaryDto();

				summaryDto.setCandidateId(employee.getCandidateId());
				summaryDto.setEmployeeId(employee.getEmployeeId());
				summaryDto.setName(employee.getName());
				summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
				summaryDto.setContactNumber(employee.getContactNumber());
				summaryDto.setEmailId(employee.getEmailId());
				summaryDto.setDateOfJoining(employee.getDateOfJoining());
				summaryDto.setDepartment(employee.getDepartment());
				summaryDto.setSubDepartment(employee.getSubDepartment());
				// summaryDto.setAssignTo(employee.getAssignTo());
				if (employee.getDesignation() != null) {
					summaryDto.setDesignation(employee.getDesignation());
				} else {
					if (candidate != null && candidate.getJobTitleDesignation() != null) {
						summaryDto.setDesignation(candidate.getJobTitleDesignation());
					} else {
						summaryDto.setDesignation(candidate.getJobTitleDesignation());
					}
				}
				// summaryDto.setTotalExperience();
				try {
					summaryDto.setJoinedCtc(candidate.getCtc());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				// summaryDto.setCurrentCtc();

				try {
					summaryDto.setServiceCommitment(candidate.getServiceCommitment());
				} catch (Exception e) {
					// TODO: handle exception
				} // summaryDto.setNumberOfWorkingDays();
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
				summaryDto.setQualification(educations.get(0).getQualification());
				summaryDto.setSpecialization(educations.get(0).getStream());
				summaryDto.setYearOfPassout(educations.get(0).getEndDate().getYear());
				// summaryDto.setResignationDate();
				// summaryDto.setActualLastWorkingDay();
				summaryDto.setEmployeeCategory(employee.getEmployeeCategory());
				/*
				 * summaryDto.setRelevantExperience(employee.getRelevantExperience());
				 * summaryDto.setWorkLocation(employee.getWorkLocation());
				 */
				summaryDtoList.add(summaryDto);
			}

		}

		return summaryDtoList;
	}

	@Override
	public EmployeeViewDto getSummaryByCandidateId(long candidateId) {
		Employee employee = this.employeeRepository.findByCandidateId(candidateId);

		if (employee != null) {
			EmployeeViewDto employeeDto = new EmployeeViewDto();
			employeeDto.setEmployeeId(employee.getEmployeeId());
			employeeDto.setName(employee.getName());
			employeeDto.setContactNumber(employee.getContactNumber());
			employeeDto.setEmailId(employee.getEmailId());
			employeeDto.setDateOfJoining(employee.getDateOfJoining());
			employeeDto.setDesignation(employee.getDesignation());
			employeeDto.setDepartment(employee.getDepartment());
			employeeDto.setEmployeeStatus(employee.getEmployeeStatus());
			// employeeDto.setRelevantExperience(employee.getRelevantExperience());
			// employeeDto.setAssignTo(employee.getAssignTo());

			return employeeDto;
		}

		return null;
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
					return "Candidate with this Email Id already exist";
				}

				if (employeeRepository.existsByContactNumber(singleEmployee.getContactNumber())) {
					return "Candidate with this Mobile No. already exist";
				}

				Onboarding onboarding = new Onboarding();
				onboarding.setCandidateId(onboardingRepository.count() + 1);
				onboarding.setCandidateName(singleEmployee.getName());
				onboarding.setCandidatesStatus(CandidatesStatus.Approved);
				onboarding.setContactNumber(singleEmployee.getContactNumber());
				onboarding.setDateOfJoining(singleEmployee.getDateOfJoining());
				onboarding.setEmailId(singleEmployee.getEmailId());
				onboarding.setCtc(singleEmployee.getJoinedCtc());
				onboarding.setJobTitleDesignation(singleEmployee.getDesignation());
				onboarding.setServiceCommitment(singleEmployee.getServiceCommitment());
				this.onboardingRepository.save(onboarding);

				Personal personal = new Personal();
				personal.setCandidateId(onboarding.getCandidateId());

				PersonalDetails personalDetails = new PersonalDetails();
				personal.setPersonalDetails(personalDetails);

				DocumentDetails documentDetails = new DocumentDetails();
				personal.setDocumentDetails(documentDetails);

				personalDetails.setFathersName(singleEmployee.getFatherName());
				personalDetails.setBloodGroup(singleEmployee.getBloodGroup());
				personalDetails.setDateOfBirth(singleEmployee.getDateOfBirth());
				personalDetails.setPersonalMailId(singleEmployee.getEmailId());
				personalDetails.setPhoneNo(singleEmployee.getContactNumber());
				// this.personalDetailsRepository.save(personalDetails);

				BankDetails bankDetails = new BankDetails();
				personal.setBankDetails(bankDetails);
				bankDetails.setAccountNo(singleEmployee.getBankAccountNumber());
				this.bankDetailsRepository.save(bankDetails);

				documentDetails.setAdharCardNo(singleEmployee.getAdharCardNo());
				documentDetails.setPanCardNo(singleEmployee.getPanCardNo());
				// this.documentsDetailsRepository.save(documentDetails);

				this.personalRepository.save(personal);

				Education education = new Education();
				education.setCandidateId(onboarding.getCandidateId());

				education.setQualification(singleEmployee.getQualification());
				education.setStream(singleEmployee.getStream());
				education.setEndDate(singleEmployee.getYearOfPassout());

				this.educationRepository.save(education);

				Employee employee = new Employee();
				employee.setCandidateId(onboarding.getCandidateId());
				employee.setEmployeeId(singleEmployee.getEmployeeId());
				employee.setName(singleEmployee.getName());
				employee.setEmployeeStatus(singleEmployee.getEmployeeStatus());
				employee.setEmployeeCategory(singleEmployee.getCategory());
				employee.setContactNumber(singleEmployee.getContactNumber());
				employee.setEmailId(singleEmployee.getEmailId());
				employee.setDateOfJoining(singleEmployee.getDateOfJoining());
				employee.setDepartment(singleEmployee.getDepartment());
				employee.setSubDepartment(singleEmployee.getSubDepartment());
				employee.setAssignTo(singleEmployee.getAssignTo());
				employee.setManager(null);
				employee.setDesignation(singleEmployee.getDesignation());
				employee.setCategoryControl(singleEmployee.getCategoryControl());
				employee.setTotalExperience(null);
				employee.setJoinedCtc(singleEmployee.getJoinedCtc());
				employee.setCurrentCtc(singleEmployee.getCurrentCtc());
				employee.setServiceCommitment(0);
				employee.setNumberOfWorkingDays(null);
				employee.setNextApprisalQuater(singleEmployee.getNextApprisalQuater());
				employee.setUanNumber(singleEmployee.getUanNumber());
				employee.setImported(true);
				this.employeeRepository.save(employee);

				// Send mail to fill personal details with link
				Long candidateId = onboarding.getCandidateId();
				String link = "http://10.10.20.9:8082/#/welcome/" + candidateId;
				String name = onboarding.getCandidateName();
				SimpleMailMessage mailMessage = new SimpleMailMessage();

				mailMessage.setFrom(sender);
				mailMessage.setTo(onboarding.getEmailId());
				mailMessage.setSubject("Fill all personal details");
				mailMessage.setText("Dear " + name + "," + "\n\r" + "\n\r"
						+ "Please click the link below and fill your information. " + "\n\r" + "\n\r" + "Link : "
						+ link);

				this.javaMailSender.send(mailMessage);
			}
		} catch (Exception e) {
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
	public String editBasicInfo(BasicInfoDto basicInfo, String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		if (employee == null) {
			throw new EntityNotFoundException("Employee not found for employeeId: " + employeeId);
		}

		employee.setEmployeeStatus(basicInfo.getEmployeeStatus());
		employee.setProbationPeriod(basicInfo.getProbationPeriod());
		employee.setEmployeeCategory(basicInfo.getEmployeeCategory());

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
	public WorkInfoDto getWorkInfo(String employeeId) {
		try {
			Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {

				WorkInfoDto workInfo = new WorkInfoDto();

				// workInfo.setAssignTo(employee.getAssignTo());
				workInfo.setDepartment(employee.getDepartment());
				workInfo.setDesignation(employee.getDesignation());
				workInfo.setSubDepartment(employee.getSubDepartment());
				workInfo.setWorkLocation(employee.getWorkLocation());
				workInfo.setCategoryControl(employee.getCategoryControl());

				return workInfo;

			} else {
				long candidateId = employee.getCandidateId();
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

				WorkInfoDto workInfo = new WorkInfoDto();

				workInfo.setDesignation(onboarding.getJobTitleDesignation());
				// workInfo.setWorkLocation(onboarding.getWorkLocation());

				return workInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new WorkInfoDto();
		}
	}

	@Override
	public BasicInfoDto getBasicInfo(String employeeId) {
		try {
			Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

			if (employee != null) {
				BasicInfoDto basicInfo = new BasicInfoDto();

				if (employee.getEmployeeStatus() != null) {
					basicInfo.setEmployeeStatus(employee.getEmployeeStatus());
				}

				if (employee.getProbationPeriod() != null) {
					basicInfo.setProbationPeriod(employee.getProbationPeriod());
				}

				if (employee.getDateOfJoining() != null) {
					basicInfo.setDateOfJoining(employee.getDateOfJoining());
				}
				if (employee.getEmployeeCategory() != null) {
					basicInfo.setEmployeeCategory(employee.getEmployeeCategory());
				}

				Period period = Period.between(employee.getDateOfJoining(), LocalDate.now());

				int years = period.getYears();
				int months = period.getMonths();

				basicInfo.setWorkExperience(years + " years " + months + " months ");

				return basicInfo;
			} else {
				return new BasicInfoDto();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BasicInfoDto();
		}
	}

	private Duration calculateWorkExperience(LocalDate dateOfJoining) {
		return Duration.between(dateOfJoining.atStartOfDay(), LocalDate.now().atStartOfDay());
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
