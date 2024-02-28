package com.hrm.main.servicesImpls;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.hrm.main.models.Helper.CtcBreakup;
import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import com.hrm.main.models.Helper.PDF;
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
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.mail.javamail.MimeMessageHelper;
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

			System.out.println("_______________________________________");
			System.out.println(candidate.getJobTitleDesignation());
			System.out.println("_______________________________________");

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
			employeeDto.setDesignation(candidate.getJobTitleDesignation());
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
		candidate.setJobTitleDesignation(appointmentInfo.getDesignation());
		// candidate.setWorkLocation(appointmentInfo.getWorkLocation());
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

	// ______________________RELEASE____________________________
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

			try {
				Employee employee = this.employeeRepository.findByCandidateId(candidateId);

				CtcBreakup ctcBreakup = new CtcBreakup();
				CtcBreakup result = ctcBreakup.createCtcBreakUp(employee.getCtc());

				String html = " <div style=\"margin-left:3%;\">\r\n"
						+ "            <h1 style=\"text-align:center;font-weight:bold;\"><u>APPOINTMENT LETTER</u></h1>\r\n"
						+ "           \r\n" + "     \r\n" + "\r\n" + "            <div style=\"margin-top:5%;\">\r\n"
						+ "        <p>Employee ID :<span style=\"font-weight:bold;\">" + employee.getEmployeeId()
						+ "</span></p>\r\n"
						+ "        <p style=\"margin-right:5%;\">Dear <span style=\"font-weight:bold;\">"
						+ employee.getName() + "</span><br><br>\r\n" + "   \r\n"
						+ "          Welcome to <span style=\"font-weight:bold;\">Envision Integrated Services Private Limited</span>.<br><br>You are appointed as <span style=\"font-weight:bold;\">"
						+ employee.getDesignation() + "</span>\r\n" + "          in Permanent role\r\n"
						+ "           w.e.f <span style=\"font-weight:bold;\">" + employee.getDateOfJoining()
						+ "</span> and your Base operations are from\r\n" + "          " + employee.getWorkLocation()
						+ ". The following are the terms and conditions of the Employment.</p>\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">COMPENSATION & BENEFITS</h2>\r\n"
						+ "        <ol style=\"margin-right:5%;text-align:justify;\">\r\n" + "          <li>\r\n"
						+ "            Your total Annual Salary (CTC) will be Rs.<span style=\"font-weight:bold;\">"
						+ employee.getCtc() + ".</span> The detailed compensation\r\n"
						+ "            structure is described in Annexure- A. </li>\r\n" + "          <li>\r\n"
						+ "            Your salary will be reviewed periodically as per Company policy. However, changes in\r\n"
						+ "            your compensation are discretionary and will be based on effective performance results\r\n"
						+ "            during the period and other relevant criteria. </li>\r\n" + "          <li>\r\n"
						+ "            Permanent employees will get additional benefit and contribution to Provident Fund.\r\n"
						+ "            Computations will be on the basic component of the remuneration package and as per\r\n"
						+ "            relevant & applicable rules and regulations. </li>\r\n" + "          <li>\r\n"
						+ "            Please note that the remuneration structure may be altered/ modified at any time without\r\n"
						+ "            prior notice and your remuneration and other terms may accordingly be altered/ modified\r\n"
						+ "            from time to time. Further, all other payments/ benefits will be governed by the\r\n"
						+ "            Company's rules as well as statutory provisions in force from time to time and subject to\r\n"
						+ "            deduction of appropriate taxes at source. </li>\r\n" + "          <li>\r\n"
						+ "            Your remuneration package is strictly confidential between you and the Company and\r\n"
						+ "            should not be discussed with any one nor divulged to anyone in any manner whatsoever.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            Employees in India are normally paid their monthly remuneration through bank every\r\n"
						+ "            month.\r\n" + "          </li>\r\n" + "        </ol>\r\n" + "   \r\n"
						+ "        <h3 style=\"font-weight:bold;\">PERIOD OF SERVICE</h3>\r\n"
						+ "        <ol style=\"margin-right:5%;text-align:justify;\">\r\n" + "          <li>\r\n"
						+ "            Executing a <span style=\"font-weight:bold;\">" + employee.getServiceCommitment()
						+ "</span>-year training cum service agreement with the company.\r\n" + "          </li>\r\n"
						+ "   \r\n" + "          <li>\r\n"
						+ "            The Probation Period is six months and you will be under evaluation. During this tenure if\r\n"
						+ "            your performance is not reaching the expectation and satisfaction level, the Probation\r\n"
						+ "            period will be extended or You will be removed from the position.\r\n"
						+ "          </li>\r\n" + "        </ol>\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">HOURS OF WORK</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"text-align:justify;margin-right:5%;\">\r\n" + "          <li>\r\n"
						+ "            Work days: 6 Days / Week.\r\n" + "          </li>\r\n" + "   \r\n"
						+ "          <li>\r\n"
						+ "            The working hours applicable to you will be dependent upon your place of posting.\r\n"
						+ "            Further, you should be prepared to work on any shift, as may be warranted by the\r\n"
						+ "            Company's/ Client's work requirements. Depending on Organizational requirement or\r\n"
						+ "            project contingencies, your working hours may be modified/ altered from time to time.\r\n"
						+ "            You may also be required to work on holidays/ weekends depending on the nature of the\r\n"
						+ "            project's.\r\n" + "          </li>\r\n" + "          <li>\r\n"
						+ "            The Company does not make overtime payments for extra hours worked, if any.\r\n"
						+ "          </li>\r\n" + "        </ol>\r\n" + "        <!-- <div>\r\n"
						+ "          <p style=\"margin-top:30px;text-align:center;font-weight:bold;\">www.actus.com</p>\r\n"
						+ "        </div> -->\r\n" + "        <!-- <div class=\"subpage\">Page 1/2</div>\r\n"
						+ "        </div> -->\r\n" + "     \r\n" + "       \r\n"
						+ "        <!-- <div class=\"page\"> -->\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">LEAVES & HOLIDAYS</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"margin-right:5%;\">\r\n" + "          <li>\r\n"
						+ "            You will be entitled for 18 days paid leaves per calendar year on a prorate basis from your\r\n"
						+ "            date of joining. Earned leave will be credited to your account on a monthly basis.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            You will be eligible for encashment of Paid leaves as per the Basic Component of your\r\n"
						+ "            salary.\r\n" + "          </li>\r\n" + "          <li>\r\n"
						+ "            As each region may have a different set of holidays, your holiday calendar will be\r\n"
						+ "            governed by your office/ client location.\r\n" + "          </li>\r\n"
						+ "        </ol>\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">UNAUTHORISED ABSENCE FROM WORK</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"margin-right:3%;\">\r\n" + "          <li>\r\n"
						+ "            Unauthorized absence for a period of 3 days or more will be treated as absconding from\r\n"
						+ "            duties and shall not be entitled to any pay, allowances and proceedings during the period\r\n"
						+ "            of such absence and will lead to termination of employment.\r\n"
						+ "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
						+ "            Absconding from duties with any damaging or improper returning of company's\r\n"
						+ "            belongings by the employee will be considered as stealing of company's property. So the\r\n"
						+ "            company has all due right to take legal action towards employee.\r\n"
						+ "          </li>\r\n" + "        </ol>\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">RETIREMENT AGE</h2>\r\n" + "   \r\n"
						+ "        <ol>\r\n" + "          <li>\r\n"
						+ "            All employees in the company shall retire on attainment of normal retirement age of 58\r\n"
						+ "            Years fixed by the company.\r\n" + "          </li>\r\n" + "        </ol>\r\n"
						+ "   \r\n" + "        <h2 style=\"font-weight:bold;\">DISPUTES</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"margin-right:5%;text-align:justify;\">\r\n" + "          <li>\r\n"
						+ "            Any disputes between yourself and the company shall be subject to the jurisdiction of and\r\n"
						+ "            be determined by a court of competent jurisdiction in Hyderabad. When you are deputed\r\n"
						+ "            overseas or assigned to work with any group company or affiliate, in the event of a\r\n"
						+ "            dispute arising due to a breach of any provision of this Agreement the company shall\r\n"
						+ "            have the right to seek relief in any court in the geographic location of your assignment\r\n"
						+ "            and the governing law shall be the applicable law of that jurisdiction.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            In the event of a dispute arising due to a violation of the non-compete provision of this\r\n"
						+ "            Agreement the company shall have the right to seek damages and injunctive relief in any\r\n"
						+ "            court in the geographic location of the customer(s) and the governing law shall be the\r\n"
						+ "            applicable law of that jurisdiction.\r\n" + "          </li>\r\n"
						+ "        </ol>\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">BACKGROUND VERIFICATION</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"margin-right:5%;text-align:justify\">\r\n" + "          <li>\r\n"
						+ "            The Company reserves the right to carry out reference verifications or background checks\r\n"
						+ "            prior to your joining the Company or during the course of your engagement with this\r\n"
						+ "            Company. Such background checks and reference verifications, amongst others, would\r\n"
						+ "            include past engagement and salary (this will include your immediate previous\r\n"
						+ "            engagement), criminal records, countries resided in or worked in, etc. The Company\r\n"
						+ "            reserves the right to carry out banned/ illegal drugs/ narcotics substance screening tests on\r\n"
						+ "            you at any point of time during your engagement. You understand and acknowledge that\r\n"
						+ "            this is a requirement and you have no objections whatsoever if such checks and\r\n"
						+ "            verifications are carried out by the Company or a third party agency engaged by the\r\n"
						+ "            Company.\r\n" + "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
						+ "            In verification the information furnished by you in your application is misstated or\r\n"
						+ "            documents submitted by you are not correct or banned/ illegal drugs/ narcotics substance\r\n"
						+ "            screening test results are positive, the Company shall, at its sole discretion be entitled to\r\n"
						+ "            forthwith terminate and/ or revoke your engagement with the Company, without further\r\n"
						+ "            reference in the matter. Further, termination under this Clause will not confer on you\r\n"
						+ "            any right to stake claim of any kind of compensation from the Company.\r\n"
						+ "          </li>\r\n" + "        </ol>\r\n" + "        <!-- <div>\r\n"
						+ "          <p style=\"margin-top:30px;text-align:center;font-weight:bold;\">www.actus.com</p>\r\n"
						+ "        </div> -->\r\n" + "        <!-- </div> -->\r\n" + "   \r\n"
						+ "        <!-- <div class=\"page\"> -->\r\n"
						+ "        <h2 style=\"font-weight:bold;\">TERMINATION OF EMPLOYMENT</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"margin-right:5%;text-align:justify\">\r\n"
						+ "          <li style=\"text-align:justify;\">\r\n" + "            Exit by Separation:<br>\r\n"
						+ "            <ol style=\"text-align:justify;margin-left:-12px;\" type=\"A\">\r\n"
						+ "              <li>\r\n" + "            Employee should serve 3 months notice period.<br>\r\n"
						+ "            </li>\r\n" + "            <li>\r\n"
						+ "            Employees have to pay Gross Salary in lieu of Notice period.<br>\r\n"
						+ "            </li>\r\n" + "            <li>\r\n"
						+ "            In the event that you decide to leave the company before a period of service\r\n"
						+ "              agreement you must pay an amount of " + employee.getBondBreakAmount()
						+ " (INR) in lieu of the\r\n" + "              breaking of service agreement.<br>\r\n"
						+ "              </li>\r\n" + "              <li>\r\n"
						+ "            The service agreement/ notice period calculated on a prorate basis from your\r\n"
						+ "            date of joining/ resignation vice-versa.\r\n" + "            </li>\r\n"
						+ "            </ol>\r\n" + "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
						+ "            However should you sign any service agreement with company as a part of your\r\n"
						+ "            employment process or later in the course of your employment in the company, you will\r\n"
						+ "            then not be entitled to terminate your employment with the company unless you comply\r\n"
						+ "            with the terms and conditions of the agreement in addition to the above.\r\n"
						+ "          </li>\r\n" + "   \r\n" + "          <li>\r\n"
						+ "            Any employee leaving the organization before completion of Service agreement from his/\r\n"
						+ "            her Date of joining will be liable for recovery of any kind of payments made to him at the\r\n"
						+ "            time of joining. (Joining Bonus, Notice Period Payment, Relocation Expenses and any\r\n"
						+ "            other payment made to him at the time of his joining.)\r\n"
						+ "          </li>\r\n" + "   \r\n" + "          <li style=\"text-align:justify;\">\r\n"
						+ "            The company shall have the right to terminate this agreement forthwith, without any\r\n"
						+ "            notice and without any salary in lieu of notice period in the event of any of the following:<br>\r\n"
						+ "            <ol style=\"text-align:justify;margin-left:-12px;\" type=\"A\">\r\n"
						+ "              <li>\r\n"
						+ "            a.Unauthorized absence for a period of 3 days or more will be treated as absconding\r\n"
						+ "              from duties.<br>\r\n" + "              </li>\r\n" + "              <li>\r\n"
						+ "            b.Breach on your part of any terms and conditions of this contract and any other\r\n"
						+ "            rules made applicable to you in respect of your employment with us.<br>\r\n"
						+ "            </li>\r\n" + "            <li>\r\n"
						+ "            c.Violation on your part of the company’s rule with regards to the authenticity and\r\n"
						+ "            information declared at the time of joining the company.<br>\r\n"
						+ "            </li>\r\n" + "            <li>\r\n"
						+ "            d.Any misconduct or failure to carry out any of your duties, confidential data and\r\n"
						+ "            obligations.<br>\r\n" + "            </li>\r\n" + "            <li>\r\n"
						+ "            e.Reducing of workforce in case of cancelation of the project or Natural calamities,\r\n"
						+ "            company has due rights to terminate the employee with one month or less than\r\n"
						+ "            one month of written notice.\r\n" + "            </li>\r\n"
						+ "            </ol>\r\n" + "          </li>\r\n" + "   \r\n" + "        </ol>\r\n" + "   \r\n"
						+ "        <h2 style=\"font-weight:bold;\">GENERAL TERMS AND CONDITIONS</h2>\r\n" + "   \r\n"
						+ "        <ol style=\"margin-right:5%;text-align:justify\">\r\n" + "          <li>\r\n"
						+ "            You can be transferred to any other location, department, function, establishment, or\r\n"
						+ "            branch of the Company or subsidiary, associate or affiliate Company in case if the\r\n"
						+ "            Company requires so for business purposes. In such case you will be governed by the\r\n"
						+ "            terms and conditions of service applicable to the new assignment.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            You shall be bound by all the policies, rules, regulations and procedures currently\r\n"
						+ "            prevailing or that may be established by the Company in future, and any modifications\r\n"
						+ "            thereof or additions thereto, as may be declared by the Company from time to time.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            During your engagement with the Company, you will, at all times, observe secrecy in\r\n"
						+ "            respect of any technical, trade or business data, customers' names/ business details or any\r\n"
						+ "            other information that might come to your knowledge or possession, which according to\r\n"
						+ "            the Company are necessarily confidential and form valuable property of the Company.\r\n"
						+ "            You shall not disclose nor cause the disclosure of any such data in any manner\r\n"
						+ "            whatsoever.\r\n" + "          </li>\r\n" + "          <li>\r\n"
						+ "            In the event of your leaving the engagement with the Company, you are expected not to\r\n"
						+ "            take up employment or enter into any type of business/ commercial association with any\r\n"
						+ "            of the Company's clients or their associates, for a period of two year from the date of\r\n"
						+ "            cessation of this engagement. You have to safeguard Envision Integrated Services Pvt.\r\n"
						+ "            Ltd and its customers Intellectual Property Rights and confidential information even after\r\n"
						+ "            termination of your engagement or business relationship with Envision Integrated\r\n"
						+ "            Services Pvt. Ltd.\r\n" + "          </li>\r\n" + "          <li>\r\n"
						+ "            All software, systems, ideas, concepts, designs, documentation or any other material\r\n"
						+ "            produced by you during the period of your engagement with Envision Integrated Services\r\n"
						+ "            Pvt. Ltd will either be Intellectual Property of Envision Integrated Services Pvt. Ltd or\r\n"
						+ "            that of its Customers. You will not have any rights to such material as described above.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            You are required to comply with all the policies of the Company including but not limited\r\n"
						+ "            to the Code of Ethical Business Conduct, the Anti-Sexual Harassment Policy and such\r\n"
						+ "            other policies, as communicated to the associates of Envision Integrated Services Pvt. Ltd\r\n"
						+ "            from time to time. In case of any violation or failure to comply with such Company\r\n"
						+ "            Policy/ policies, the Employee shall be subjected to the disciplinary action as per company\r\n"
						+ "            policy.\r\n" + "          </li>\r\n" + "          <li>\r\n"
						+ "            Any invalid provision or any gap or uncertainty of any provision in the Appointment\r\n"
						+ "            letter that becomes apparent when performing the Appointment letter shall be replaced,\r\n"
						+ "            interpreted or Supplemented as the case may be in such a manner that the intended\r\n"
						+ "            purpose of the Appointment letter will be achieved.\r\n" + "          </li>\r\n"
						+ "          <li>\r\n"
						+ "            Should any provision of this Appointment letter be or become ineffective, or be held to be\r\n"
						+ "            invalid, this shall not affect the validity of the remaining provisions hereof. Any invalid\r\n"
						+ "            provision or any gap or uncertainty of any provision in the Appointment letter that\r\n"
						+ "            becomes apparent when performing the Appointment letter shall be replaced, interpreted\r\n"
						+ "            or Supplemented as the case may be in such a manner that the intended purpose of the\r\n"
						+ "            Appointment letter will be achieved.\r\n" + "          </li>\r\n"
						+ "          <li>\r\n"
						+ "            From the date of last working day full & final settlement & respective documents will be\r\n"
						+ "            cleared on/before 45 days.\r\n" + "          </li>\r\n" + "          <li>\r\n"
						+ "            Under Income Tax Act, it is obligatory for all the employees to submit their Permanent\r\n"
						+ "            Account Number (PAN) to company at the time of joining for the purpose of TDS (Tax\r\n"
						+ "            Deduction at Source). In case if the employee fails to provide the PAN card details,\r\n"
						+ "            employer will not provide the FORM16 to the employee.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            You are to devote your full time, attention and ability to the interest of the company.\r\n"
						+ "          </li>\r\n" + "          <li>\r\n"
						+ "            By signing a copy of this letter, you are confirming that familiar with Envision Integrated\r\n"
						+ "            Services Pvt. Ltd's policies. Envision Integrated Services Pvt. Ltd. reserves the right to\r\n"
						+ "            interpret, change, suspend or terminate any of its benefits, policy plans or programs in\r\n"
						+ "            accordance with its needs from time to time.\r\n" + "          </li>\r\n"
						+ "        </ol>\r\n"
						+ "        <p>We take this opportunity to welcome you to the Envision IS family and wish you a satisfying\r\n"
						+ "          engagement with us.</p>\r\n" + "   \r\n"
						+ "          <h2 style=\"font-weight:bold;\">ACCEPTANCE OF JOINING</h2>\r\n"
						+ "          <p>The terms and conditions of this Appointment Letter are fully acceptable to me. I shall report\r\n"
						+ "            for duties on <span style=\"font-weight:bold;\">" + employee.getDateOfJoining()
						+ ".</span></p>\r\n" + "   \r\n" + "            <p>Sincerely,</p>\r\n"
						+ "            <p>For  <span style=\"font-weight:bold;\">Envision Integrated Services Private Limited</span>.</p>\r\n"
						+ "\r\n" + "            <div style=\"display:flex;\">\r\n" + "              <div >\r\n"
						+ "                <img src=\"{{'data:image/jpg;base64,' + viewdata1?.authorisedSignature}}\" style=\"height:100px;width:150px;margin-top:20px;\" />\r\n"
						+ "                <p style=\"margin-top:10px;\">Authorized Signature</p>\r\n"
						+ "               \r\n" + "                 \r\n" + "                </div>\r\n"
						+ "             \r\n" + "               \r\n"
						+ "                  <div style=\"margin-left:500px;\">\r\n"
						+ "                    <p style=\"\">Employee Name: <span style=\"font-weight:bold;font-size:0.83vw;\">"
						+ employee.getName() + "</span></p>\r\n" + "                   \r\n"
						+ "                    <img src=\"" + employee.getSign()
						+ "\" style=\"height:100px;width:150px;margin-top:5px;\" />\r\n"
						+ "                      <p style=\"margin-top:3px;\">Employee Signature </p>\r\n"
						+ "                   \r\n" + "                     \r\n" + "                  </div>\r\n"
						+ "           \r\n" + "            </div>\r\n" + "</div>\r\n" + "           \r\n"
						+ "          <!-- <div>\r\n"
						+ "            <p style=\"margin-top:30px;text-align:center;font-weight:bold;\">www.actus.com</p>\r\n"
						+ "          </div> -->\r\n" + "          <!-- </div> -->\r\n"
						+ "          <!-- <div class=\"page\"> -->\r\n"
						+ "            <h2 style=\"text-align:center;\">ANNEXURE – A</h2>\r\n" + "       \r\n"
						+ "          <table class=\"ctcbreakup\" style=\"width:80%\">\r\n"
						+ "          <tr class=\"breakup\">\r\n"
						+ "            <td class=\"salary\" >EMPLOYEE NAME</td>\r\n"
						+ "            <td class=\"salary\" colspan=\"2\">" + employee.getName() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
						+ "            <td class=\"salary\" >EMPLOYEE ID</td>\r\n"
						+ "            <td class=\"salary\" colspan=\"2\">" + employee.getEmployeeId() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
						+ "            <td class=\"salary\" >DESIGNATION</td>\r\n"
						+ "            <td class=\"salary\" colspan=\"2\">" + employee.getDesignation() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
						+ "            <td class=\"salary\" >DATE OF JOINING</td>\r\n"
						+ "            <td class=\"salary\" colspan=\"2\">" + employee.getDateOfJoining() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
						+ "            <td class=\"salary\" >DATE OF RELEASING</td>\r\n"
						+ "            <td class=\"salary\" colspan=\"2\">" + employee.getDateOfJoining() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr>\r\n"
						+ "            <td class=\"salary\" colspan=\"3\" style=\"text-align:center;font-size:17px;\">EARNINGS - A</td>\r\n"
						+ "            </tr>\r\n" + "          <tr style=\"background-color: #dddddd;\">\r\n"
						+ "            <th class=\"salary\">PARTICULARS</th>\r\n"
						+ "            <th class=\"salary\">Annual</th>\r\n"
						+ "            <th class=\"salary\">Monthly</th>\r\n" + "          </tr>\r\n"
						+ "          <tr>\r\n" + "            <td class=\"salary\">BASIC</td>\r\n"
						+ "            <td class=\"salary\" style='text-align: end;'>" + result.getBasicSalary() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyBasicSalary() + "</td>\r\n"
						+ "          </tr>\r\n" + "          <tr>\r\n"
						+ "            <td class=\"salary\">HOUSE RENT ALLOWANCE</td>\r\n"
						+ "            <td class=\"salary\">" + result.getHouseRentAllowance() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyHouseRentAllowance() + "</td>\r\n"
						+ "          </tr>\r\n" + "          <!-- <tr>\r\n" + "            <td>CONVEYANCE</td>\r\n"
						+ "            <td>____________</td>\r\n" + "            <td>____________</td>\r\n"
						+ "          </tr> -->\r\n" + "          <tr>\r\n"
						+ "            <td class=\"salary\">SPECIAL ALLOWANCE</td>\r\n"
						+ "            <td class=\"salary\">" + result.getSpecialAllowance() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlySpecialAllowance() + "</td>\r\n"
						+ "          </tr>\r\n" + "          <tr>\r\n"
						+ "            <td class=\"salary\">MEDICAL</td>\r\n" + "            <td class=\"salary\">"
						+ result.getMedicalAllowance() + "</td>\r\n" + "            <td class=\"salary\">"
						+ result.getMonthlyMedicalAllowance() + "</td>\r\n" + "          </tr>\r\n"
						+ "          <tr>\r\n" + "            <td class=\"salary\">TELEPHONE</td>\r\n"
						+ "            <td class=\"salary\">" + result.getTelephoneAllowance() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyTelephoneAllowance() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr>\r\n"
						+ "            <td class=\"salary\">LEAVE TRAVEL ALLOWANCE</td>\r\n"
						+ "            <td class=\"salary\">" + result.getLeaveTravelAllowance() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyLeaveTravelAllowance() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <!-- <tr>\r\n"
						+ "            <td>SHIFT ALLOWANCES</td>\r\n" + "            <td>______________</td>\r\n"
						+ "            <td>______________</td>\r\n" + "            </tr> -->\r\n"
						+ "            <tr style=\"background-color: #dddddd;\">\r\n"
						+ "            <td class=\"salary\">GROSS SALARY</td>\r\n" + "            <td class=\"salary\">"
						+ result.getGrossSalary() + "</td>\r\n" + "            <td class=\"salary\">"
						+ result.getMonthlyGrossSalary() + "</td>\r\n" + "            </tr>\r\n"
						+ "            <tr>\r\n" + "            <td class=\"salary\">EMPLOYEE PF</td>\r\n"
						+ "            <td class=\"salary\">" + result.getEmployeePF() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyEmployeePF() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr>\r\n"
						+ "            <td class=\"salary\">GRATUITY</td>\r\n" + "            <td class=\"salary\">"
						+ result.getGratuity() + "</td>\r\n" + "            <td class=\"salary\">"
						+ result.getMonthlyGratuity() + "</td>\r\n" + "            </tr>\r\n" + "            <tr>\r\n"
						+ "            <td class=\"salary\">PROFESSIONAL TAX</td>\r\n"
						+ "            <td class=\"salary\">" + result.getProfessionalTax() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyProfessionalTax() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr style=\"background-color: #dddddd;\">\r\n"
						+ "            <td class=\"salary\">NET TAKE HOME </td>\r\n"
						+ "            <td class=\"salary\">" + result.getNetTakeHome() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyNetTakeHome() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr>\r\n"
						+ "            <td class=\"salary\">EMPLOYER PF</td>\r\n" + "            <td class=\"salary\">"
						+ result.getEmployerPF() + "</td>\r\n" + "            <td class=\"salary\">"
						+ result.getMonthlyEmployerPF() + "</td>\r\n" + "            </tr>\r\n" + "            <tr>\r\n"
						+ "            <td class=\"salary\">GROUP INSURANCE</td>\r\n"
						+ "            <td class=\"salary\">" + result.getGroupInsurance() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlyGroupInsurance() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <!-- <tr>\r\n"
						+ "            <td>PERFORMACE LINKED\r\n" + "              PAY(PLP)</td>\r\n"
						+ "            <td>______________</td>\r\n" + "            <td>______________</td>\r\n"
						+ "            </tr> -->\r\n" + "            <tr>\r\n" + "   \r\n" + "            </tr>\r\n"
						+ "            <tr style=\"background-color: #dddddd;\">\r\n"
						+ "            <td class=\"salary\">TOTAL COST TO COMPANY</td>\r\n"
						+ "            <td class=\"salary\">" + result.getTotalCTC() + "</td>\r\n"
						+ "            <td class=\"salary\">" + result.getMonthlytotalCTC() + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr class=\"breakup\">\r\n"
						+ "            <td class=\"salary\" colspan=\"3\" style=\"text-align:center;\">{{totalcost  | uppercase}}</td>\r\n"
						+ "            </tr>\r\n" + "          </table>\r\n" + "          <br>\r\n"
						+ "          <p>The terms and conditions of this Appointment Letter are fully acceptable to me. I shall report\r\n"
						+ "          for duties on <span style=\"font-weight:bold;\">" + employee.getDateOfJoining()
						+ "</span>.</p>\r\n" + "   \r\n" + "          <p>Sincerely,</p>\r\n"
						+ "          <p>For <span style=\"font-weight:bold;\">Envision Integrated Services Private Limited</span>.\r\n"
						+ "\r\n" + "            <div style=\"display:flex;\">\r\n" + "            <div >\r\n"
						+ "              <img src=\"{{'data:image/jpg;base64,' + viewdata1?.authorisedSignature}}\" style=\"height:100px;width:150px;margin-top:20px;\" />\r\n"
						+ "              <p style=\"margin-top:10px;\">Authorized Signature</p>\r\n"
						+ "             \r\n" + "               \r\n" + "              </div>\r\n" + "           \r\n"
						+ "             \r\n" + "                <div style=\"margin-left:500px;margin-top:-5px\">\r\n"
						+ "                  <p style=\"\">Employee Name : <span style=\"font-weight:bold;font-size:0.83vw;\">"
						+ employee.getName() + "</span></p>\r\n" + "                 \r\n"
						+ "                  <img src=\"" + employee.getSign()
						+ "\" style=\"height:100px;width:150px;margin-top:5px;\" />\r\n"
						+ "                    <p style=\"margin-top:3px;\">Employee Signature </p>\r\n"
						+ "                 \r\n" + "                   \r\n" + "                </div>\r\n"
						+ "         \r\n" + "          </div>\r\n" + "        </div>";

				String recipient = employee.getEmailId();
				String subject = "Appointment Letter";
				String body = "Dear " + employee.getName() + "," + "\n\r" + "\n\r" + "Congratulations !!"
						+ " You are selected for Envision Integrated Services. Please Check Appointment Letter. ";

				String pdfFilePath = "C:\\Users\\Rameshrao.k\\Appointment Letter/19.46 Appointment Letter  "
						+ employee.getName() + ".pdf";
				FileOutputStream fileOutputStream = new FileOutputStream(pdfFilePath);

				try {
					MimeMessage mimeMessage = javaMailSender.createMimeMessage();
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

					mimeMessageHelper.setFrom(sender);
					mimeMessageHelper.setTo(recipient);
					mimeMessageHelper.setSubject(subject);
					mimeMessageHelper.setText(body);

					FileSystemResource file = new FileSystemResource(pdfFilePath);
					mimeMessageHelper.addAttachment(file.getFilename(), file);

					javaMailSender.send(mimeMessage);
				} catch (MessagingException e) {
					e.printStackTrace();
				}

				HtmlConverter.convertToPdf(html, fileOutputStream);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);
			candidate.setCandidatesStatus(CandidatesStatus.Approved);

			Employee employee = this.employeeRepository.findByCandidateId(candidateId);

			employee.setEmployeeStatus(EmployeeStatus.Active);
			employee.setAppointmentLetter(appointmentLetterDto.getAppointmentLetter());
			employee.setCandidateId(candidateId);

			this.employeeRepository.save(employee);

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

		// employeeGenerateDto.setJobTitleDesignation();

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
//__________________CREATE____________________
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
		employee.setServiceCommitment(appointmentLetterDto.getBondPeriod());
		employee.setBondBreakAmount(appointmentLetterDto.getBondBreakAmount());
		employee.setEmailId(appointmentLetterDto.getEmailId());
		employee.setContactNumber(appointmentLetterDto.getContactNumber());
		employee.setJobTitle(appointmentLetterDto.getJobTitle());
		employee.setAuthorisedSignature(appointmentLetterDto.getAuthorisedSignature());
		employee.setSign(appointmentLetterDto.getSign());
		employee.setEmployeeStatus(EmployeeStatus.Active);

		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);
		System.out.println("------------------------------------");
		System.out.println("Status Of Candidate : " + onboarding.getCandidatesStatus());
		onboarding.setCandidatesStatus(CandidatesStatus.Approved);
		System.out.println("Status Of Candidate : " + onboarding.getCandidatesStatus());

		this.onboardingRepository.save(onboarding);
		System.out.println("Status Of Candidate : "
				+ this.onboardingRepository.findByCandidateId(candidateId).getCandidatesStatus());
		System.out.println("------------------------------------");

		// employee.setAppointmentLetter(appointmentLetterDto.getAppointmentLetter());

		this.employeeRepository.save(employee);

		return "Appointment Letter Created";
	}

	@Override
	public CtcBreakup check(long candidateId) {

		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

		long ctc = onboarding.getCtc();
		CtcBreakup breakup = new CtcBreakup();
		return breakup.createCtcBreakUp(ctc);

	}

}
