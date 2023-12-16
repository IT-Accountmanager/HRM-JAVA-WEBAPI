package com.hrm.main.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Agreement;
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
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.main.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrExecutiveWorkApprovalDto;
import com.hrm.main.payloads.HrManagerAgreementApprovalDto;
import com.hrm.main.payloads.HrManagerDto;
import com.hrm.main.payloads.HrManagerEducationApprovalDto;
import com.hrm.main.payloads.HrManagerFamilyApprovalDto;
import com.hrm.main.payloads.HrManagerPersonalApprovalDto;
import com.hrm.main.payloads.HrManagerWorkApprovalDto;
import com.hrm.main.repositories.IAgreementRepository;
import com.hrm.main.repositories.IEducationRepository;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IFamilyRepository;
import com.hrm.main.repositories.IHRManagerRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.IHRManagerService;

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
					hrManager.setJobTitle(onboarding.getJobTitle());
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
			candidate.setCandidatesStatus(CandidatesStatus.Rejected);
			this.onboardingRepository.save(candidate);
			return 1;

		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	@Override
	public EmployeeGenerateDto generateEmployee(long candidateId) {

		if (employeeRepository.existsByCandidateId(candidateId)) {
			return null;
		}

		ApprovalStatus personalApprovalStatus = this.personalRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		ApprovalStatus familyApprovalStatus = this.familyRepository.findAllByCandidateId(candidateId).get(0)
				.getHrManagerApprovalStatus();

		ApprovalStatus educationApprovalStatus = this.educationRepository.findAllByCandidateId(candidateId).get(0)
				.getHrManagerApprovalStatus();

		/*
		 * ApprovalStatus workApprovalStatus =
		 * this.workRepository.findAllWorkByCandidateId(candidateId).get(0)
		 * .getHrManagerApprovalStatus();
		 */

		ApprovalStatus agreementApprovalStatus = this.agreementRepository.findByCandidateId(candidateId)
				.getHrManagerApprovalStatus();

		if (agreementApprovalStatus == ApprovalStatus.Approved && personalApprovalStatus == ApprovalStatus.Approved
				&& familyApprovalStatus == ApprovalStatus.Approved && educationApprovalStatus == ApprovalStatus.Approved
		/* && workApprovalStatus == ApprovalStatus.Approve */)

		{
			Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);
			candidate.setCandidatesStatus(CandidatesStatus.Approved);

			if (employeeRepository.existsByEmailIdOrContactNumber(candidate.getEmailId(),
					candidate.getContactNumber())) {
				// Employee with the same email or contact number already exists, return the
				// appropriate message
				return null;
			}
			EmployeeGenerateDto employeeDto = new EmployeeGenerateDto();
			long nextEmployeeIdNumber = this.employeeRepository.count() + 1;
			employeeDto.setEmployeeId(String.format("EIS%05d", nextEmployeeIdNumber));

			employeeDto.setName(candidate.getCandidateName());
			employeeDto.setDesignation(candidate.getJobTitle());
			employeeDto.setWorkLocation(candidate.getWorkLocation());
			employeeDto.setDateOfJoining(candidate.getDateOfJoining());
			/*
			 * employeeDto.setDteOfReleasing(this.workRepository.findAllWorkByCandidateId(
			 * candidateId).stream().findFirst() .get().getRelievedDate());
			 */
			employeeDto.setCtc(candidate.getCtc());
			employeeDto.setServiceCommitment(candidate.getServiceCommitment());
			employeeDto.setServiceBreakAmount(candidate.getServiceBreakAmount());
			employeeDto.setEmailId(candidate.getEmailId());
			employeeDto.setContactNumber(candidate.getContactNumber());
			employeeDto.setStatus(EmployeeStatus.Active);

			this.employeeRepository.save(this.modelMapper.map(employeeDto, Employee.class));

			return employeeDto;
		}
		return null;
	}

}
