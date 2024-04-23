package com.hrm.servicesImpls;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.helper.EnumCollection.CategoryControl;
import com.hrm.helper.EnumCollection.CategoryControll;
import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.EmployeeCategory;
import com.hrm.helper.EnumCollection.EmployeeStatus;
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

	private static final Logger logger = LoggerFactory.getLogger(SummaryServiceImpl.class);

	/*
	 * @Override public List<SummaryDto> getAll() { List<Object[]> findAll =
	 * this.employeeRepository.summaryData(); List<SummaryDto> summaryDtoList = new
	 * ArrayList<>();
	 * 
	 * for (Object[] row : findAll) { if (row.length > 0 && row[0] instanceof
	 * Employee) { Employee employee = (Employee) row[0]; SummaryDto summaryDto =
	 * new SummaryDto();
	 * 
	 * if (employee.isImported()) { populateSummaryDtoForImportedEmployee(employee,
	 * summaryDto); } else { populateSummaryDtoForNonImportedEmployee(employee,
	 * summaryDto); }
	 * 
	 * summaryDtoList.add(summaryDto); } else {
	 * 
	 * System.err.println("Error: First element of row is not an Employee object.");
	 * } } return summaryDtoList; }
	 * 
	 * private void populateSummaryDtoForImportedEmployee(Employee employee,
	 * SummaryDto summaryDto) { long candidateId = employee.getCandidateId();
	 * 
	 * Personal personal = this.personalRepository.findByCandidateId(candidateId);
	 * Education education =
	 * this.educationRepository.findByCandidateId(candidateId); Onboarding
	 * onboarding = this.onboardingRepository.findByCandidateId(candidateId);
	 * 
	 * summaryDto.setCandidateId(candidateId);
	 * summaryDto.setEmployeeId(employee.getEmployeeId());
	 * summaryDto.setName(employee.getName());
	 * summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
	 * summaryDto.setEmployeeCategory(employee.getEmployeeCategory());
	 * summaryDto.setContactNumber(employee.getContactNumber());
	 * summaryDto.setEmailId(employee.getEmailId());
	 * summaryDto.setDateOfJoining(employee.getDateOfJoining());
	 * summaryDto.setDepartment(employee.getDepartment());
	 * summaryDto.setSubDepartment(employee.getSubDepartment());
	 * summaryDto.setAssignTo(employee.getAssignTo());
	 * summaryDto.setDesignation(employee.getDesignation());
	 * summaryDto.setCategoryControl(employee.getCategoryControl());
	 * summaryDto.setTotalExperience(null);
	 * summaryDto.setJoinedCtc(employee.getJoinedCtc());
	 * summaryDto.setCurrentCtc(employee.getCurrentCtc());
	 * summaryDto.setServiceCommitment(onboarding.getServiceCommitment());
	 * summaryDto.setNumberOfWorkingDays(employee.getNumberOfWorkingDays());
	 * summaryDto.setNextApprisalQuater(employee.getNextApprisalQuater());
	 * summaryDto.setDateOfBirth(personal.getPersonalDetails().getDateOfBirth());
	 * summaryDto.setBloodGroup(personal.getPersonalDetails().getBloodGroup());
	 * summaryDto.setFatherName(personal.getPersonalDetails().getFathersName());
	 * summaryDto.setEmergencyContact(null); summaryDto.setPermanentAddress(null);
	 * summaryDto.setTemporaryAddress(null);
	 * summaryDto.setAadharCardNumber(personal.getDocumentDetails().getAdharCardNo()
	 * ); summaryDto.setPanCardNumber(personal.getDocumentDetails().getPanCardNo());
	 * summaryDto.setUanNumber(employee.getUanNumber());
	 * summaryDto.setBankAccountNumber(personal.getBankDetails().getAccountNo());
	 * summaryDto.setQualification(education.getQualification());
	 * summaryDto.setSpecialization(education.getStream());
	 * summaryDto.setYearOfPassout(0); summaryDto.setResignationDate(null);
	 * summaryDto.setActualLastWorkingDay(null); }
	 * 
	 * private void populateSummaryDtoForNonImportedEmployee(Employee employee,
	 * SummaryDto summaryDto) { long candidateId = employee.getCandidateId();
	 * 
	 * Onboarding candidate =
	 * this.onboardingRepository.findByCandidateId(candidateId); Personal details =
	 * this.personalRepository.findByCandidateId(candidateId); List<Education>
	 * educations = this.educationRepository.findAllByCandidateId(candidateId);
	 * 
	 * summaryDto.setCandidateId(candidateId);
	 * summaryDto.setEmployeeId(employee.getEmployeeId());
	 * summaryDto.setName(employee.getName());
	 * summaryDto.setEmployeeStatus(employee.getEmployeeStatus());
	 * summaryDto.setContactNumber(employee.getContactNumber());
	 * summaryDto.setEmailId(employee.getEmailId());
	 * summaryDto.setDateOfJoining(employee.getDateOfJoining());
	 * summaryDto.setDepartment(employee.getDepartment());
	 * summaryDto.setSubDepartment(employee.getSubDepartment()); //
	 * summaryDto.setAssignTo(employee.getAssignTo()); if (employee.getDesignation()
	 * != null) { summaryDto.setDesignation(employee.getDesignation()); } else { if
	 * (candidate != null && candidate.getJobTitleDesignation() != null) {
	 * summaryDto.setDesignation(candidate.getJobTitleDesignation()); } else {
	 * summaryDto.setDesignation(candidate.getJobTitleDesignation()); } } //
	 * summaryDto.setTotalExperience(); try {
	 * summaryDto.setJoinedCtc(candidate.getCtc()); } catch (Exception e) {
	 * System.out.println(e.getMessage()); } // summaryDto.setCurrentCtc();
	 * 
	 * try { summaryDto.setServiceCommitment(candidate.getServiceCommitment()); }
	 * catch (Exception e) { // TODO: handle exception } //
	 * summaryDto.setNumberOfWorkingDays(); // summaryDto.setNextApprisalQuater();
	 * summaryDto.setDateOfBirth(details.getPersonalDetails().getDateOfBirth());
	 * summaryDto.setBloodGroup(details.getPersonalDetails().getBloodGroup());
	 * summaryDto.setFatherName(details.getPersonalDetails().getFathersName()); //
	 * summaryDto.setEmergencyContact();
	 * summaryDto.setPermanentAddress((details.getAddressDetails().getPermanentAdd()
	 * .getHouseNo()) + ", " +
	 * (details.getAddressDetails().getPermanentAdd().getArea()) + ", near " +
	 * (details.getAddressDetails().getPermanentAdd().getLandmark()) + ", " +
	 * (details.getAddressDetails().getPermanentAdd().getCity()) + ", " +
	 * (details.getAddressDetails().getPermanentAdd().getState()) + ", " +
	 * (details.getAddressDetails().getPermanentAdd().getPincode()));
	 * summaryDto.setTemporaryAddress((details.getAddressDetails().getPresentAdd().
	 * getHouseNo()) + ", " +
	 * (details.getAddressDetails().getPresentAdd().getArea()) + ", near " +
	 * (details.getAddressDetails().getPresentAdd().getLandmark()) + ", " +
	 * (details.getAddressDetails().getPresentAdd().getCity()) + ", " +
	 * (details.getAddressDetails().getPresentAdd().getState()) + ", " +
	 * (details.getAddressDetails().getPresentAdd().getPincode()));
	 * summaryDto.setAadharCardNumber(details.getDocumentDetails().getAdharCardNo())
	 * ; summaryDto.setPanCardNumber(details.getDocumentDetails().getPanCardNo());
	 * // summaryDto.setUanNumber();
	 * summaryDto.setBankAccountNumber(details.getBankDetails().getAccountNo());
	 * summaryDto.setQualification(educations.get(0).getQualification());
	 * summaryDto.setSpecialization(educations.get(0).getStream());
	 * summaryDto.setYearOfPassout(educations.get(0).getEndDate().getYear()); //
	 * summaryDto.setResignationDate(); // summaryDto.setActualLastWorkingDay();
	 * summaryDto.setEmployeeCategory(employee.getEmployeeCategory()); /*
	 * summaryDto.setRelevantExperience(employee.getRelevantExperience());
	 * summaryDto.setWorkLocation(employee.getWorkLocation());
	 */
//	}*/

	@Override
	public String getAll() {
		List<Object[]> summaryData = this.employeeRepository.findSummaryData();
		ObjectMapper objectMapper = new ObjectMapper();
		/*
		 * e.employee_id, e.name, e.employee_status, e.employee_category,
		 * e.contact_number, e.email_id, e.date_of_joining, e.department,
		 * e.sub_department, e.manager, e.designation, e.category_control,
		 * e.total_experience, e.joined_ctc, e.current_ctc, e.service_commitment,
		 * e.number_of_working_days, e.next_apprisal_quater, pd.date_of_birth,
		 * pd.blood_group, pd.fathers_name, dd.adhar_card_no, dd.pan_card_no,
		 * e.uan_number, bd.account_no, e.resignation_date, e.last_working_day
		 */
		ArrayNode summaryArray = objectMapper.createArrayNode();

		try {
			for (Object[] summary : summaryData) {
				ObjectNode summaryNode = objectMapper.createObjectNode();
				summaryNode.put("employee_id", (String) summary[0]);
				summaryNode.put("name", (String) summary[1]);
				summaryNode.put("employee_status", getEmployeeStatus(((Byte) summary[2]).intValue()).toString());
				summaryNode.put("employee_category", getEmployeeCategory(((Byte) summary[2]).intValue()).toString());
				summaryNode.put("contact_number", String.valueOf((Long) summary[4]));
				summaryNode.put("email_id", (String) summary[5]);
				summaryNode.put("date_of_joining", ((Date) summary[6]).toString());
				if (summary[7] != null) {
					String departmentName = mapDepartment((Byte) summary[7]);
					summaryNode.put("department", departmentName);
				}

				if (summary[8] != null) {
					String subDepartmentName = mapSubDepartment((Byte) summary[8]);
					if (!subDepartmentName.equals(summaryNode.get("department").asText())) {
						summaryNode.put("sub_department", subDepartmentName);
					}
				}
				if (summary[9] != null) {
					summaryNode.put("manager", (String) summary[9]);
				}
				if (summary[10] != null) {
					Designation designation = Designation.values()[(Byte) summary[10]];
					summaryNode.put("designation", designation.toString());
				}
				if (summary[11] != null) {
					int categoryControlOrdinal = (Byte) summary[11];
					CategoryControl[] values = CategoryControl.values();
					if (categoryControlOrdinal >= 0 && categoryControlOrdinal < values.length) {
						CategoryControl categoryControl = values[categoryControlOrdinal];
						summaryNode.put("category_control", categoryControl.name());
					}
				}
				if (summary[12] != null) {
					summaryNode.put("total_experience", (String) summary[12]);
				}
				if (summary[13] != null) {
					summaryNode.put("joined_ctc", ((Long) summary[13]).toString());
				}
				if (summary[14] != null) {
					summaryNode.put("current_ctc", ((Long) summary[14]).toString());
				}
				if (summary[15] != null) {
					summaryNode.put("service_commitment", ((Float) summary[15]).intValue());
				}
				if (summary[16] != null) {
					summaryNode.put("number_of_working_days", (String) summary[16]);
				}
				if (summary[17] != null) {
					summaryNode.put("next_apprisal_quater", (short) summary[17]);
				}
				summaryNode.put("date_of_birth", ((Date) summary[18]).toString());
				summaryNode.put("blood_group", getBloodGroup(((Byte) summary[19]).intValue()).toString());
				summaryNode.put("fathers_name", (String) summary[20]);
				summaryNode.put("adhar_card_no", (String) summary[21]);
				summaryNode.put("pan_card_no", (String) summary[22]);
				summaryNode.put("uan_number", (String) summary[23]);
				summaryNode.put("account_no", (String) summary[24]);
				if (summary[25] != null) {
					summaryNode.put("resignation_date", ((Date) summary[25]).toString());
				}
				if (summary[26] != null) {
					summaryNode.put("last_working_day", ((Date) summary[26]).toString());
				}
				summaryNode.put("qualification", (String) summary[27]);
				summaryNode.put("end_date", ((Date) summary[28]).toString());
				summaryNode.put("stream", (String) summary[29]);
				summaryNode.put("candidate_id", ((Long) summary[30]).toString());
				summaryNode.put("Manager_id", (String) summary[31]);
				summaryNode.put("number_of_working_days", (String) summary[32]);

				summaryArray.add(summaryNode);

			}
			String jsonData = objectMapper.writeValueAsString(summaryArray);
			logger.info("Retrieved summary data successfully.");

			return jsonData;
		} catch (Exception e) {

			logger.error("An error occurred while fetching summary data. Reason: {}", e.getMessage(), e);
			return "An error occurred while fetching summary data :" + e.getMessage();
		}

	}

	public EmployeeStatus getEmployeeStatus(int statusValue) {
		return (statusValue == 0) ? EmployeeStatus.Active : EmployeeStatus.Inactive;
	}

	public enum BloodGroup {
		A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE
	}

	public com.hrm.helper.EnumCollection.BloodGroup getBloodGroup(int statusValue) {
		switch (statusValue) {
		case 0:
			return com.hrm.helper.EnumCollection.BloodGroup.A_POSITIVE;
		case 1:
			return com.hrm.helper.EnumCollection.BloodGroup.A_NEGATIVE;
		case 2:
			return com.hrm.helper.EnumCollection.BloodGroup.B_POSITIVE;
		case 3:
			return com.hrm.helper.EnumCollection.BloodGroup.B_NEGATIVE;
		case 4:
			return com.hrm.helper.EnumCollection.BloodGroup.AB_POSITIVE;
		case 5:
			return com.hrm.helper.EnumCollection.BloodGroup.AB_NEGATIVE;
		case 6:
			return com.hrm.helper.EnumCollection.BloodGroup.O_POSITIVE;
		case 7:
			return com.hrm.helper.EnumCollection.BloodGroup.O_NEGATIVE;
		default:
			return null;
		}
	}

	public EmployeeCategory getEmployeeCategory(int categoryValue) {
		switch (categoryValue) {
		case 0:
			return EmployeeCategory.Working;
		case 1:
			return EmployeeCategory.WalkOut;
		case 2:
			return EmployeeCategory.Terminated;
		case 3:
			return EmployeeCategory.Absconded;
		case 4:
			return EmployeeCategory.NotReporting;
		case 5:
			return EmployeeCategory.Bench;
		case 6:
			return EmployeeCategory.LongLeave;
		case 7:
			return EmployeeCategory.Contractual;
		case 8:
			return EmployeeCategory.ProbationPeriod;
		case 9:
			return EmployeeCategory.TrainingPeriod;
		case 10:
			return EmployeeCategory.NoticePeriod;
		default:
			return null;
		}
	}

	private String mapDepartment(byte value) {
		for (Departments department : Departments.values()) {
			for (Departments.Department subDepartment : department.getSubdepartments()) {
				if (subDepartment.ordinal() == value) {
					return department.name();
				}
			}
		}
		return null;
	}

	private String mapSubDepartment(byte value) {
		for (Departments department : Departments.values()) {
			for (Departments.Department subDepartment : department.getSubdepartments()) {
				if (subDepartment.ordinal() == value) {
					return subDepartment.name();
				}
			}
		}
		return null;
	}

	public static String getDesignation(int ordinal) {
		Designation[] designations = Designation.values();
		if (ordinal >= 0 && ordinal < designations.length) {
			return designations[ordinal].toString();
		} else {
			return null;
		}
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
			employeeDto.setManager(employee.getManager());

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
				List<Employee> emps = this.employeeRepository.findAll();

				long maxEmployeeSn = 0;
				for (Employee emp : emps) {
					long currentEmployeeSn = emp.getEmployeeSn();
					if (currentEmployeeSn > maxEmployeeSn) {
						maxEmployeeSn = currentEmployeeSn;
					}
				}
				maxEmployeeSn++;

				List<Onboarding> findAll = this.onboardingRepository.findAll();

				long maxCandidateId = 0;
				for (Onboarding candidate : findAll) {
					long currentCandidateId = candidate.getCandidateId();
					if (currentCandidateId > maxCandidateId) {
						maxCandidateId = currentCandidateId;
					}
				}
				maxCandidateId++;
				Onboarding onboarding = new Onboarding();
				onboarding.setCandidateId(maxCandidateId);
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
				employee.setEmployeeId(String.format("EIS%05d", maxEmployeeSn));
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
				employee.setServiceCommitment(singleEmployee.getServiceCommitment());
				employee.setNumberOfWorkingDays(singleEmployee.getNumberOfWorkingDays());
				employee.setNextApprisalQuater(singleEmployee.getNextApprisalQuater());
				employee.setUanNumber(singleEmployee.getUanNumber());
				employee.setImported(true);
				employee.setLastWorkingDay(singleEmployee.getLastWorkingDay());
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
			boolean employeeExists = this.employeeRepository.existsByEmployeeId(employeeId);

			if (employeeExists) {
				Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

				WorkInfoDto workInfo = new WorkInfoDto();

				// workInfo.setAssignTo(employee.getAssignTo());
				workInfo.setDepartment(employee.getDepartment());
				workInfo.setDesignation(employee.getDesignation());
				workInfo.setSubDepartment(employee.getSubDepartment());
				workInfo.setWorkLocation(employee.getWorkLocation());
				workInfo.setCategoryControl(employee.getCategoryControl());

				return workInfo;

			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
