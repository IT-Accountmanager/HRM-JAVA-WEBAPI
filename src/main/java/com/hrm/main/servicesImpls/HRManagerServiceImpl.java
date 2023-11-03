package com.hrm.main.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Education;
import com.hrm.main.models.Family;
import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import com.hrm.main.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.main.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrExecutiveWorkApprovalDto;
import com.hrm.main.payloads.HrManagerDto;
import com.hrm.main.payloads.HrManagerEducationApprovalDto;
import com.hrm.main.payloads.HrManagerFamilyApprovalDto;
import com.hrm.main.payloads.HrManagerPersonalApprovalDto;
import com.hrm.main.payloads.HrManagerWorkApprovalDto;
import com.hrm.main.repositories.IEducationRepository;
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
					hrManager.setBondPeriod(onboarding.getBondPeriod());
					hrManager.setBondBreakAmount(onboarding.getBondBreakAmount());
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
	public HrManagerWorkApprovalDto workApproval(HrManagerWorkApprovalDto hrManagerWorkApprovalDto, long candidateId) {
		Work exisitingWork = this.workRepository.findByCandidateId(candidateId);

		modelMapper.map(hrManagerWorkApprovalDto, exisitingWork);

		this.workRepository.save(exisitingWork);
		return hrManagerWorkApprovalDto;
	}

	@Override
	public HrManagerWorkApprovalDto getWorkApproval(long candidateId) {
		Work work = this.workRepository.findByCandidateId(candidateId);
		HrManagerWorkApprovalDto map = this.modelMapper.map(work, HrManagerWorkApprovalDto.class);
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

}
