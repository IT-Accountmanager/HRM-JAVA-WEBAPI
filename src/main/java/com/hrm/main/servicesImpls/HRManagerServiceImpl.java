package com.hrm.main.servicesImpls;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Agreement;
import com.hrm.main.models.BackgroundVerification;
import com.hrm.main.models.CreateAppointmentLetterDto;
import com.hrm.main.models.Education;
import com.hrm.main.models.Employee;
import com.hrm.main.models.Family;
import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import com.hrm.main.payloads.AppointmentLetterReleaseOrRejectDto;
import com.hrm.main.payloads.AuthorizedSignDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.HrManagerAgreementApprovalDto;
import com.hrm.main.payloads.HrManagerBackgroundVerificationDto;
import com.hrm.main.payloads.HrManagerDto;
import com.hrm.main.payloads.HrManagerEducationApprovalDto;
import com.hrm.main.payloads.HrManagerFamilyApprovalDto;
import com.hrm.main.payloads.HrManagerPersonalApprovalDto;
import com.hrm.main.payloads.HrManagerWorkApprovalDto;
import com.hrm.main.repositories.IAgreementRepository;
import com.hrm.main.repositories.IBackgroundVerificationRepository;
import com.hrm.main.repositories.IEducationRepository;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IFamilyRepository;
import com.hrm.main.repositories.IHRManagerRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.IHRManagerService;
import javax.mail.util.ByteArrayDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.websocket.Decoder;
import javax.activation.DataSource;

@Service

public class HRManagerServiceImpl implements IHRManagerService {

	@Autowired
	private IHRManagerRepository hRManagerRepository;
	@Autowired
	private IOnboardingRepository onboardingRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private IPersonalRepository personalRepository;
	@Autowired
	private IFamilyRepository familyRepository;
	@Autowired
	private IEducationRepository educationRepository;
	@Autowired
	private IWorkRepository workRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IAgreementRepository agreementRepository;
	@Autowired
	private IBackgroundVerificationRepository backgroundVerificationRepository;
	@Autowired
	JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public boolean postCandidatesInHrManager(CandidatesStatus status) {
		List<Onboarding> findAllByCandidatesStatus = this.onboardingRepository.findAllByCandidatesStatus(status);
		boolean verify = false;

		List<HRManager> candidatesHRManager = findAllByCandidatesStatus.stream().filter(onboarding -> {
			HRManager existingCandidates = this.hRManagerRepository.findByCandidateId(onboarding.getCandidateId());
			return existingCandidates == null;
		})

				/*
				 * List<HRExecutive> hrExecutiveList = findAllByCandidatesStatus.stream()
				 * .filter(onboarding -> { HRExecutive existingExecutive =
				 * this.hRExecutiveRepository.findByCandidateId(onboarding.getCandidateId());
				 * return existingExecutive == null; })
				 */

				.map(onboarding -> {
					HRManager hrManager = new HRManager();
					hrManager.setJobTitle(onboarding.getJobTitleDesignation());
					hrManager.setCandidateId(onboarding.getCandidateId());
					hrManager.setCandidateName(onboarding.getCandidateName());
					hrManager.setContactNumber(onboarding.getContactNumber());
					hrManager.setEmailId(onboarding.getEmailId());
					hrManager.setServiceCommitment(onboarding.getServiceCommitment());
					hrManager.setServiceBreakAmount(onboarding.getServiceBreakAmount());
					hrManager.setCtc(onboarding.getCtc());
					hrManager.setStatus(onboarding.getCandidatesStatus());

					return hrManager;

				}).collect(Collectors.toList());

		List<HRManager> success = this.hRManagerRepository.saveAll(candidatesHRManager);

		return !success.isEmpty();

		/*
		 * try { HRManager manager = this.hRManagerRepository.findByCandidateId(status);
		 * if (manager.getId() > 0) { return "All detail are added : " +
		 * manager.getId(); } } catch (Exception e) { e.getMessage(); } return
		 * "All details are not added";
		 */ }

	@Override
	public List<HrManagerDto> getAllCandidates(CandidatesStatus status) {

		List<Onboarding> allCandidates = this.onboardingRepository.findAllByCandidatesStatus(status);

		List<HrManagerDto> filterCandidates = allCandidates.stream()
				.filter(candidate -> candidate.getHrExecutiveSubmission() == HrSubmission.Submit)
				.map(onboarding -> modelMapper.map(onboarding, HrManagerDto.class)).collect(Collectors.toList());

		return filterCandidates;
	}

	@Override
	public HrManagerDto getCandidate(long candidateId) {
		Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);
		HrManagerDto dto = modelMapper.map(candidate, HrManagerDto.class);
		return dto;
	}

	@Override
	public String updateHRManager(HRManager hrManager, Integer id) {

		try {

			if (this.hRManagerRepository.existsById(id)) {

				hrManager.setId(id);

				this.hRManagerRepository.save(hrManager);

				return "Id no. : " + id + " is updated ";

			} else {
				return "Id no. : " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return "Id no. :" + id + " is updated ";
	}

	@Override
	public String deleteHRManager(Integer id) {
		try {
			hRManagerRepository.deleteById(id);

			return "Id no. :" + id + " is deleted successfully";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. :" + id + " is not deleted";
	}

	@Override
	public Long nextValue() {
		return this.hRManagerRepository.count();
	}

	@Override
	public HrManagerPersonalApprovalDto personalApproval(HrManagerPersonalApprovalDto hrManagerPersonalApprovalDto,
			long candidateId) {
		Personal existingPersonal = this.personalRepository.findByCandidateId(candidateId);
		modelMapper.map(hrManagerPersonalApprovalDto, existingPersonal);
		this.personalRepository.save(existingPersonal);
		return hrManagerPersonalApprovalDto;
	}

	@Override
	public HrManagerPersonalApprovalDto getPersonalApproval(long candidateId) {
		Personal candidate = this.personalRepository.findByCandidateId(candidateId);
		HrManagerPersonalApprovalDto map = this.modelMapper.map(candidate, HrManagerPersonalApprovalDto.class);
		return map;
	}

	@Override
	public HrManagerFamilyApprovalDto familyApproval(HrManagerFamilyApprovalDto hrManagerFamilyApprovalDto,
			long candidateId) {

		List<Family> families = this.familyRepository.findAllByCandidateId(candidateId);
		families.forEach(family -> {
			family.setHrManagerApprovalStatus(hrManagerFamilyApprovalDto.getHrManagerApprovalStatus());
			family.setHrManagerRemark(hrManagerFamilyApprovalDto.getHrManagerRemark());
			this.familyRepository.save(family);
		});
		return hrManagerFamilyApprovalDto;
	}

	@Override
	public HrManagerFamilyApprovalDto getFamilyApproval(long candidateId) {
		List<Family> listFamily = this.familyRepository.findAllByCandidateId(candidateId);
		HrManagerFamilyApprovalDto approval = new HrManagerFamilyApprovalDto();
		if (!listFamily.isEmpty()) {
			Family firstFamily = listFamily.get(0);
			approval.setHrManagerApprovalStatus(firstFamily.getHrManagerApprovalStatus());
			approval.setHrManagerRemark(firstFamily.getHrManagerRemark());
		}
		return approval;
	}

	@Override
	public HrManagerEducationApprovalDto educationApproval(HrManagerEducationApprovalDto hrManagerEducationApprovalDto,
			long candidateId) {
		List<Education> existingEducation = this.educationRepository.findAllByCandidateId(candidateId);
		existingEducation.forEach(education -> {
			education.setHrManagerApprovalStatus(hrManagerEducationApprovalDto.getHrManagerApprovalStatus());
			education.setHrManagerRemark(hrManagerEducationApprovalDto.getHrManagerRemark());
			this.educationRepository.save(education);
		});

		return hrManagerEducationApprovalDto;
	}

	@Override
	public HrManagerEducationApprovalDto getEducationApproval(long candidateId) {
		List<Education> listEducation = this.educationRepository.findAllByCandidateId(candidateId);
		HrManagerEducationApprovalDto approval = new HrManagerEducationApprovalDto();
		if (!listEducation.isEmpty()) {
			Education firstEducation = listEducation.get(0);
			approval.setHrManagerApprovalStatus(firstEducation.getHrManagerApprovalStatus());
			approval.setHrManagerRemark(firstEducation.getHrManagerRemark());
		}
		return approval;
	}

	@Override
	public int workApproval(HrManagerWorkApprovalDto hrManagerWorkApprovalDto, long candidateId) {
		try {
			List<Work> works = this.workRepository.findAllWorkByCandidateId(candidateId);
			works.forEach(work -> {
				work.setHrManagerApprovalStatus(hrManagerWorkApprovalDto.getHrManagerApprovalStatus());
				work.setHrManagerRemark(hrManagerWorkApprovalDto.getHrManagerRemark());
				this.workRepository.save(work);
			});
			return 1;

		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public HrManagerWorkApprovalDto getWorkApproval(long candidateId) {
		List<Work> listWork = this.workRepository.findAllWorkByCandidateId(candidateId);
		HrManagerWorkApprovalDto approval = new HrManagerWorkApprovalDto();
		if (!listWork.isEmpty()) {
			Work firstWork = listWork.get(0);
			approval.setHrManagerApprovalStatus(firstWork.getHrManagerApprovalStatus());
			approval.setHrManagerRemark(firstWork.getHrManagerRemark());
		} else {
			approval.setHrManagerApprovalStatus(ApprovalStatus.Pending);
		}
		return approval;
	}

	@Override
	public HrManagerAgreementApprovalDto agreementApproval(HrManagerAgreementApprovalDto hrManagerAgreementApprovalDto,
			long candidateId) {
		Agreement existingAgreement = this.agreementRepository.findByCandidateId(candidateId);
		modelMapper.map(hrManagerAgreementApprovalDto, existingAgreement);
		this.agreementRepository.save(existingAgreement);
		return hrManagerAgreementApprovalDto;
	}

	@Override
	public HrManagerAgreementApprovalDto getAgreementApproval(long candidateId) {
		Agreement agreement = this.agreementRepository.findByCandidateId(candidateId);
		HrManagerAgreementApprovalDto map = this.modelMapper.map(agreement, HrManagerAgreementApprovalDto.class);
		return map;
	}

	@Override
	public Integer rejectHrManager(long candiateId) {

		try {
			Onboarding candidate = this.onboardingRepository.findByCandidateId(candiateId);
			candidate.setHrExecutiveSubmission(HrSubmission.Reject);
			candidate.setCandidatesStatus(CandidatesStatus.HRManagerRejected);
			this.onboardingRepository.save(candidate);
			return 1;

		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public EmployeeGenerateDto getAppointmentLetter(long candidateId) {

		// ---------------------------------------------------------------------------------------------------------------------

		/*
		 * if (employeeRepository.existsByCandidateId(candidateId)) { return null; }
		 */

		ApprovalStatus personalApprovalStatus = this.personalRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		ApprovalStatus familyApprovalStatus = this.familyRepository.findAllByCandidateId(candidateId).get(0)
				.getHrManagerApprovalStatus();

		ApprovalStatus educationApprovalStatus = this.educationRepository.findAllByCandidateId(candidateId).get(0)
				.getHrManagerApprovalStatus();

		/*
		 * List<Work> workList =
		 * this.workRepository.findAllWorkByCandidateId(candidateId);
		 * 
		 * if (!workList.isEmpty()) { ApprovalStatus workApprovalStatus =
		 * workList.get(0).getHrManagerApprovalStatus(); } else { return
		 * "Work details not found for the candidate"; }
		 */

		ApprovalStatus agreementApprovalStatus = this.agreementRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		ApprovalStatus bgvApprovalStatus = this.backgroundVerificationRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		if (agreementApprovalStatus == ApprovalStatus.Approved && personalApprovalStatus == ApprovalStatus.Approved
				&& familyApprovalStatus == ApprovalStatus.Approved && educationApprovalStatus == ApprovalStatus.Approved
				/* && workApprovalStatus == ApprovalStatus.Approved */ && bgvApprovalStatus == ApprovalStatus.Approved)

		{
			Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);
			candidate.setCandidatesStatus(CandidatesStatus.Approved);

			/*
			 * if (employeeRepository.existsByEmailIdOrContactNumber(candidate.getEmailId(),
			 * candidate.getContactNumber())) { return null; }
			 */

			EmployeeGenerateDto employeeDto = new EmployeeGenerateDto();
			/*
			 * long nextEmployeeIdNumber = this.employeeRepository.count() + 1;
			 * employeeDto.setEmployeeId(String.format("EIS%05d", nextEmployeeIdNumber));
			 */
			employeeDto.setName(candidate.getCandidateName());
			employeeDto.setCandidateId(candidateId);
			employeeDto.setJobTitleDesignation(candidate.getJobTitleDesignation());
			employeeDto.setWorkLocation(candidate.getWorkLocation());
			employeeDto.setDateOfJoining(candidate.getDateOfJoining());
			employeeDto.setCtc(candidate.getCtc());
			employeeDto.setServiceCommitment(candidate.getServiceCommitment());
			employeeDto.setServiceBreakAmount(candidate.getServiceBreakAmount());
			employeeDto.setEmailId(candidate.getEmailId());
			employeeDto.setContactNumber(candidate.getContactNumber());
			employeeDto.setEmployeeStatus(EmployeeStatus.Active);

			Agreement agreement = this.agreementRepository.findByCandidateId(candidateId);
			/*
			 * if (agreement == null) { return null; }
			 */

			byte[] sign = agreement.getSign();
			/*
			 * if (sign == null) { return null; }
			 */

			employeeDto.setSign(sign);

			// java.util.Base64.Decoder decoder = Base64.getDecoder();

			/*
			 * if (appointmentLetter.appointmentLetterBase64 != null) { while
			 * (appointmentLetter.appointmentLetterBase64.length() % 4 != 0) {
			 * appointmentLetter.appointmentLetterBase64 += "="; }
			 * employeeDto.setAppointmentLetter(decoder.decode(appointmentLetter.
			 * appointmentLetterBase64)); } else { return null; }
			 */

			// Continue with the rest of your logic...

			/*
			 * while (employeeDto.signBase64.length() % 4 != 0) { employeeDto.signBase64 +=
			 * "="; }
			 */

			// employeeDto.setSign(decoder.decode(employeeDto.signBase64));

			// this.employeeRepository.save(this.modelMapper.map(employeeDto,
			// Employee.class));

			return employeeDto;
		}
		return null;

		// ----------------------------------------------------------------------

		/*
		 * EmployeeGenerateDto generate = new EmployeeGenerateDto();
		 * 
		 * Onboarding onboarding =
		 * this.onboardingRepository.findByCandidateId(candidateId);
		 * 
		 * generate.setCandidateId(candidateId);generate.setContactNumber(onboarding.
		 * getContactNumber());generate.setCtc(onboarding.getCtc());generate.
		 * setDateOfJoining(onboarding.getDateOfJoining());generate.setDesignation(
		 * onboarding.getJobTitle());generate.setEmailId(onboarding.getEmailId());
		 * generate.setName(onboarding.getCandidateName());generate.
		 * setServiceBreakAmount(onboarding.getServiceBreakAmount());generate.
		 * setServiceCommitment(onboarding.getServiceCommitment());generate.setSign(this
		 * .agreementRepository.findByCandidateId(candidateId).getSign());generate.
		 * setWorkLocation(onboarding.getWorkLocation());
		 * 
		 * return generate;
		 */
	}

	// --------------------------------------------------------------------------------------------------------------------

	@Override
	public String editAppointment(EmployeeGenerateDto appointmentInfo, long candidateId) {

		Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);

		candidate.setCandidateId(candidateId);
		candidate.setCandidateName(appointmentInfo.getName());
		candidate.setJobTitleDesignation(appointmentInfo.getJobTitleDesignation());
		candidate.setWorkLocation(appointmentInfo.getWorkLocation());
		candidate.setDateOfJoining(appointmentInfo.getDateOfJoining());
		candidate.setCtc(appointmentInfo.getCtc());
		candidate.setServiceCommitment(appointmentInfo.getServiceCommitment());
		candidate.setServiceBreakAmount(appointmentInfo.getServiceBreakAmount());
		candidate.setEmailId(appointmentInfo.getEmailId());
		candidate.setContactNumber(appointmentInfo.getContactNumber());

		this.onboardingRepository.save(candidate);

		return "Appointment saved";
	}

	// --------------------------------------------------------------------------------------------------------------------

	/*
	 * @Override public String editAppointment(EmployeeGenerateDto appointmentInfo,
	 * long candidateId) {
	 * 
	 * if (employeeRepository.existsByCandidateId(candidateId)) { return
	 * "Candidate Already Existed"; }
	 * 
	 * if (appointmentInfo == null) { return "Invalid appointmentInfo"; }
	 * 
	 * Onboarding onboarding =
	 * this.onboardingRepository.findByCandidateId(candidateId);
	 * 
	 * if (onboarding == null) { return
	 * "Onboarding record not found for candidateId: " + candidateId; }
	 * 
	 * try {
	 * 
	 * onboarding.setCandidateId(candidateId);
	 * 
	 * Employee employee = new Employee();
	 * 
	 * employee.setCandidateId(candidateId); long nextEmployeeIdNumber =
	 * this.employeeRepository.count() + 1;
	 * employee.setEmployeeId(String.format("EIS%05d", nextEmployeeIdNumber));
	 * 
	 * employee.setContactNumber(appointmentInfo.getContactNumber());
	 * employee.setCtc(appointmentInfo.getCtc());
	 * employee.setDateOfJoining(appointmentInfo.getDateOfJoining());
	 * employee.setJobTitle(appointmentInfo.getDesignation());
	 * employee.setName(appointmentInfo.getName());
	 * onboarding.setServiceBreakAmount(appointmentInfo.getServiceBreakAmount()); //
	 * Employee Generate
	 * onboarding.setServiceCommitment(appointmentInfo.getServiceCommitment());//
	 * Employee Generate
	 * employee.setWorkLocation(appointmentInfo.getWorkLocation());
	 * 
	 * while (appointmentInfo.authorisedSignatureBase64.length() % 4 != 0) {
	 * appointmentInfo.authorisedSignatureBase64 += "="; }
	 * 
	 * Base64.Decoder decoder = Base64.getDecoder();
	 * employee.setAuthorisedSignature(decoder.decode(appointmentInfo.
	 * authorisedSignatureBase64));
	 * 
	 * this.onboardingRepository.save(onboarding);
	 * this.employeeRepository.save(employee);
	 * 
	 * return "Appointment Saved"; } catch (Exception e) { return
	 * "Error updating appointment: " + e.getMessage(); } }
	 */

	// ____________________________________________________________________________________________________________

	/*
	 * @Override public EmployeeGenerateDto generateEmployee(long candidateId) {
	 * 
	 * 
	 * if (employeeRepository.existsByCandidateId(candidateId)) { return null; }
	 * 
	 * 
	 * ApprovalStatus personalApprovalStatus =
	 * this.personalRepository.findByCandidateId(candidateId)
	 * .getHrManagerApprovalStatus();
	 * 
	 * ApprovalStatus familyApprovalStatus =
	 * this.familyRepository.findAllByCandidateId(candidateId).get(0)
	 * .getHrManagerApprovalStatus();
	 * 
	 * ApprovalStatus educationApprovalStatus =
	 * this.educationRepository.findAllByCandidateId(candidateId).get(0)
	 * .getHrManagerApprovalStatus();
	 * 
	 * 
	 * ApprovalStatus workApprovalStatus =
	 * this.workRepository.findAllWorkByCandidateId(candidateId).get(0)
	 * .getHrManagerApprovalStatus();
	 * 
	 * 
	 * ApprovalStatus agreementApprovalStatus =
	 * this.agreementRepository.findByCandidateId(candidateId)
	 * .getHrManagerApprovalStatus();
	 * 
	 * if (agreementApprovalStatus == ApprovalStatus.Approved &&
	 * personalApprovalStatus == ApprovalStatus.Approved && familyApprovalStatus ==
	 * ApprovalStatus.Approved && educationApprovalStatus == ApprovalStatus.Approved
	 * && workApprovalStatus == ApprovalStatus.Approve )
	 * 
	 * { Onboarding candidate =
	 * this.onboardingRepository.findByCandidateId(candidateId); //
	 * candidate.setCandidatesStatus(CandidatesStatus.Approved);
	 * 
	 * if (employeeRepository.existsByEmailIdOrContactNumber(candidate.getEmailId(),
	 * candidate.getContactNumber())) { // Employee with the same email or contact
	 * number already exists, return the // appropriate message return null; }
	 * EmployeeGenerateDto employeeDto = new EmployeeGenerateDto(); long
	 * nextEmployeeIdNumber = this.employeeRepository.count() + 1;
	 * employeeDto.setEmployeeId(String.format("EIS%05d", nextEmployeeIdNumber));
	 * 
	 * employeeDto.setName(candidate.getCandidateName());
	 * employeeDto.setDesignation(candidate.getJobTitle());
	 * employeeDto.setWorkLocation(candidate.getWorkLocation());
	 * employeeDto.setDateOfJoining(candidate.getDateOfJoining());
	 * 
	 * employeeDto.setDteOfReleasing(this.workRepository.findAllWorkByCandidateId(
	 * candidateId).stream().findFirst() .get().getRelievedDate());
	 * 
	 * employeeDto.setCtc(candidate.getCtc());
	 * employeeDto.setServiceCommitment(candidate.getServiceCommitment());
	 * employeeDto.setServiceBreakAmount(candidate.getServiceBreakAmount());
	 * employeeDto.setEmailId(candidate.getEmailId());
	 * employeeDto.setContactNumber(candidate.getContactNumber());
	 * employeeDto.setStatus(EmployeeStatus.Active);
	 * employeeDto.setSign(this.agreementRepository.findByCandidateId(candidateId).
	 * getSign());
	 * 
	 * this.employeeRepository.save(this.modelMapper.map(employeeDto,
	 * Employee.class));
	 * 
	 * return employeeDto; } return null; }
	 */

	// ___________________________________________________________________________________________________

	@Override
	public String bgvApproval(HrManagerBackgroundVerificationDto bgv, long candidateId) {
		BackgroundVerification existingBgv = this.backgroundVerificationRepository.findByCandidateId(candidateId);
		modelMapper.map(bgv, existingBgv);
		this.backgroundVerificationRepository.save(existingBgv);
		return "Successfully added";
	}

	@Override
	public HrManagerBackgroundVerificationDto getBgvApproval(long candidateId) {
		BackgroundVerification backgroundVerification = this.backgroundVerificationRepository
				.findByCandidateId(candidateId);
		HrManagerBackgroundVerificationDto map = this.modelMapper.map(backgroundVerification,
				HrManagerBackgroundVerificationDto.class);
		return map;
	}

	@Override
	public String releaseAppointmentLetter(long candidateId, CreateAppointmentLetterDto appointmentLetterDto) {

		ApprovalStatus personalApprovalStatus = this.personalRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		ApprovalStatus familyApprovalStatus = this.familyRepository.findAllByCandidateId(candidateId).get(0)
				.getHrManagerApprovalStatus();

		ApprovalStatus educationApprovalStatus = this.educationRepository.findAllByCandidateId(candidateId).get(0)
				.getHrManagerApprovalStatus();

		/*
		 * List<Work> workList =
		 * this.workRepository.findAllWorkByCandidateId(candidateId);
		 * 
		 * if (!workList.isEmpty()) { ApprovalStatus workApprovalStatus =
		 * workList.get(0).getHrManagerApprovalStatus(); } else { return
		 * "Work details not found for the candidate"; }
		 */

		ApprovalStatus agreementApprovalStatus = this.agreementRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		if (agreementApprovalStatus == ApprovalStatus.Approved && personalApprovalStatus == ApprovalStatus.Approved
				&& familyApprovalStatus == ApprovalStatus.Approved && educationApprovalStatus == ApprovalStatus.Approved
		/* && workApprovalStatus == ApprovalStatus.Approved */)

		{
			Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);
			candidate.setCandidatesStatus(CandidatesStatus.Approved);

			/*
			 * if (employeeRepository.existsByEmailIdOrContactNumber(candidate.getEmailId(),
			 * candidate.getContactNumber())) { return
			 * "Employee with the same email or contact number already exists !"; }
			 */

			// AppointmentLetterReleaseOrRejectDto employeeDto = new
			// AppointmentLetterReleaseOrRejectDto();

			Employee employee = this.employeeRepository.findByCandidateId(candidateId);

			employee.setEmployeeStatus(EmployeeStatus.Active);
			employee.setAppointmentLetter(appointmentLetterDto.getAppointmentLetter());

			this.employeeRepository.save(employee);
			// this.employeeRepository.save(this.modelMapper.map(employeeDto,
			// Employee.class));

			return "Appointment Letter Release";
		}
		return "Error in releasing Appointment Letter";
	}

	@Override
	public EmployeeGenerateDto getReleaseAppointmentLetter(long candidateId) {

		Employee employee = this.employeeRepository.findByCandidateId(candidateId);

		if (employee == null) {
			return null;
		}

		EmployeeGenerateDto employeeGenerateDto = modelMapper.map(employee, EmployeeGenerateDto.class);

		return employeeGenerateDto;
	}

	/*
	 * @Override public String getReleaseAppointmentLetter(long candidateId) {
	 * 
	 * Onboarding onboarding =
	 * this.onboardingRepository.findByCandidateId(candidateId);
	 * 
	 * if (onboarding == null) { return "Employee Not Found"; } else {
	 * 
	 * MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	 * 
	 * try { MimeMessageHelper mimeMessageHelper = new
	 * MimeMessageHelper(mimeMessage, true);
	 * 
	 * mimeMessageHelper.setFrom(sender);
	 * mimeMessageHelper.setTo(onboarding.getEmailId());
	 * mimeMessageHelper.setSubject("Appointment Letter");
	 * 
	 * mimeMessageHelper.addAttachment(this.employeeRepository.findByCandidateId(
	 * candidateId). getAppointmentLetter());
	 * 
	 * 
	 * // Get the byte array content of the appointment letter byte[]
	 * appointmentLetterBytes =
	 * this.employeeRepository.findByCandidateId(candidateId)
	 * .getAppointmentLetter();
	 * 
	 * // Convert byte array to DataSource DataSource dataSource = new
	 * ByteArrayDataSource(appointmentLetterBytes, "application/octet-stream");
	 * 
	 * // Add the attachment with a file name (e.g., "appointment_letter.pdf")
	 * 
	 * mimeMessageHelper.addAttachment("appointment_letter.pdf",
	 * (jakarta.activation.DataSource) dataSource);
	 * 
	 * 
	 * FileSystemResource fileSystemResource = new FileSystemResource(new
	 * File(this.employeeRepository.findByCandidateId(candidateId).
	 * getAppointmentLetter()));
	 * 
	 * mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),
	 * fileSystemResource);
	 * 
	 * 
	 * javaMailSender.send(mimeMessage); return "Mail Sent Successfully"; } catch
	 * (MessagingException e) { e.printStackTrace(); }
	 * 
	 * return "Error while sending mail!!!";
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public String createAppointmentLetter(CreateAppointmentLetterDto appointmentLetterDto, long candidateId) {
		Boolean existed = this.employeeRepository.existsByCandidateId(candidateId);

		if (existed) {
			return "Employee Already Existed of Candidate Id : " + candidateId;
		}
		Employee employee = new Employee();

		employee.setCandidateId(candidateId);
		long nextEmployeeIdNumber = this.employeeRepository.count() + 1;
		employee.setEmployeeId(String.format("EIS%05d", nextEmployeeIdNumber));
		employee.setName(appointmentLetterDto.getName());
		employee.setDesignation(appointmentLetterDto.getDesignation());
		employee.setWorkLocation(appointmentLetterDto.getWorkLocation());
		employee.setDateOfJoining(appointmentLetterDto.getDateOfJoining());
		employee.setCtc(appointmentLetterDto.getCtc());
		employee.setBondPeriod(appointmentLetterDto.getBondPeriod());
		employee.setBondBreakAmount(appointmentLetterDto.getBondBreakAmount());
		employee.setEmailId(appointmentLetterDto.getEmailId());
		employee.setContactNumber(appointmentLetterDto.getContactNumber());
		employee.setDesignation(appointmentLetterDto.getDesignation());
		employee.setJobTitle(appointmentLetterDto.getJobTitle());
		employee.setAuthorisedSignature(appointmentLetterDto.getAuthorisedSignature());
		employee.setSign(appointmentLetterDto.getSign());
		// employee.setAppointmentLetter(appointmentLetterDto.getAppointmentLetter());

		this.employeeRepository.save(employee);

		return "Appointment Letter Created";
	}

}
