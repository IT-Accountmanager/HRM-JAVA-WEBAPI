
package com.hrm.main.servicesImpls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Education;
import com.hrm.main.models.Family;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Profile;
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.main.payloads.ProfileSummaryDto;
import com.hrm.main.repositories.IEducationRepository;
import com.hrm.main.repositories.IFamilyRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.repositories.IProfileRepository;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.IProfileService;

@Service
public class ProfileServiceImpl implements IProfileService {

	@Autowired
	IProfileRepository profileRepository;
	@Autowired
	IOnboardingRepository onboardingRepository;
	@Autowired
	IPersonalRepository personalRepository;
	@Autowired
	IFamilyRepository familyRepository;
	@Autowired
	IEducationRepository educationRepository;
	@Autowired
	IWorkRepository workRepository;

	@Override
	public List<Onboarding> getPendingOnboardings(CandidatesStatus status) {
		return this.onboardingRepository.findAllByCandidatesStatus(status);
	}

	@Override
	public Onboarding getOnboardingById(Integer id) {
		return this.onboardingRepository.findById(id).get();
	}

	@Override
	public int createProfile(Profile profile) {
		Profile save = this.profileRepository.save(profile);
		return save.getProfileId();
	}

	@Override
	public int createPersonalDetails(PersonalDetails personalDetails) {

		return 0;
	}

	@Override
	public int updatingCandidatesStatus(long candidateId) {

		int result = 0;
		int p = 0;
		int f = 0;
		int e = 0;
		int w = 0;
		try {
			Personal personal = this.personalRepository.findByCandidateId(candidateId);
			DetailsSubmissionStatus personalSubmissionStatus = personal.getPersonalSubmissionStatus();

			if (personalSubmissionStatus == DetailsSubmissionStatus.submitted) {
				p = 1;
			}

			List<Family> CandidatesFamilyList = this.familyRepository.findByCandidateId(candidateId);

			boolean allSubmitted = CandidatesFamilyList.stream()
					.allMatch(fl -> fl.getFamilySubmissionStatus() == DetailsSubmissionStatus.submitted);

			if (allSubmitted) {
				f = 1;
			}

			Education education = this.educationRepository.findByCandidateId(candidateId);
			DetailsSubmissionStatus educationSubmissionStatus = education.getEducationSubmissionStatus();

			if (educationSubmissionStatus == DetailsSubmissionStatus.submitted) {
				e = 1;
			}

			Work work = this.workRepository.findByCandidateId(candidateId);
			DetailsSubmissionStatus workSubmissionStatus = work.getWorkSubmissionStatus();

			if (workSubmissionStatus == DetailsSubmissionStatus.submitted) {
				w = 1;
			}

			if (p == 1 && f == 1 && e == 1 && w == 1)
				result = 1;

			if (result == 1) {
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);
				onboarding.setCandidatesStatus(CandidatesStatus.Inreview);
				this.onboardingRepository.save(onboarding);
			}
		} catch (Exception e2) {
			e2.getMessage();
		}
		return result;
	}

}
