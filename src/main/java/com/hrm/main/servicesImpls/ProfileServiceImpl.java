
package com.hrm.main.servicesImpls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Agreement;
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
import com.hrm.main.repositories.IAgreementRepository;
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
	@Autowired
	IAgreementRepository agreementRepository;

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

	/*
	 * @Override public int updatingCandidatesStatus(long candidateId) {
	 * 
	 * int result = 0; int p = 0; int f = 0; int e = 0; int w = 0; try { Personal
	 * personal = this.personalRepository.findByCandidateId(candidateId);
	 * DetailsSubmissionStatus personalSubmissionStatus =
	 * personal.getPersonalSubmissionStatus();
	 * 
	 * if (personalSubmissionStatus == DetailsSubmissionStatus.submitted) { p = 1; }
	 * 
	 * List<Family> FamilyList =
	 * this.familyRepository.findAllByCandidateId(candidateId);
	 * 
	 * boolean allFamilySubmitted = FamilyList.stream() .allMatch(family ->
	 * family.getFamilySubmissionStatus() == DetailsSubmissionStatus.submitted);
	 * 
	 * if (allFamilySubmitted) { f = 1; }
	 * 
	 * List<Education> educationList =
	 * this.educationRepository.findAllByCandidateId(candidateId); boolean
	 * allEducationSubmitted = educationList.stream().allMatch( education ->
	 * education.getEducationSubmissionStatus() ==
	 * DetailsSubmissionStatus.submitted);
	 * 
	 * if (allEducationSubmitted) { e = 1; } List<Work> workList =
	 * this.workRepository.findAllWorkByCandidateId(candidateId); boolean
	 * allWorkSubmitted = workList.stream() .allMatch(work ->
	 * work.getWorkSubmissionStatus() == DetailsSubmissionStatus.submitted);
	 * 
	 * if (allWorkSubmitted) { w = 1; }
	 * 
	 * if (p == 1 && f == 1 && e == 1 && w == 1) { result = 1; }
	 * 
	 * if (result == 1) { Onboarding onboarding =
	 * this.onboardingRepository.findByCandidateId(candidateId);
	 * onboarding.setCandidatesStatus(CandidatesStatus.Inreview);
	 * this.onboardingRepository.save(onboarding); } } catch (Exception e2) {
	 * e2.getMessage(); } return result; }
	 */

	@Override
	public int updatingCandidatesStatus(long candidateId) {

		int result = 0;

		try {
			DetailsSubmissionStatus agreementSubmissionStatus = this.agreementRepository.findByCandidateId(candidateId)
					.getAgreementSubmissionStatus();
			DetailsSubmissionStatus personalSubmissionStatus = this.personalRepository.findByCandidateId(candidateId)
					.getPersonalSubmissionStatus();

			List<Family> familyList = this.familyRepository.findAllByCandidateId(candidateId);
			List<Education> educationList = this.educationRepository.findAllByCandidateId(candidateId);
			List<Work> workList = this.workRepository.findAllWorkByCandidateId(candidateId);

			// Check if personal details are submitted
			if (personalSubmissionStatus == DetailsSubmissionStatus.Submitted) {
				// Check if all other sections are also submitted
				boolean allFamilySubmitted = !familyList.isEmpty() && familyList.stream()
						.allMatch(family -> family.getFamilySubmissionStatus() == DetailsSubmissionStatus.Submitted);

				boolean allEducationSubmitted = !educationList.isEmpty() && educationList.stream().allMatch(
						education -> education.getEducationSubmissionStatus() == DetailsSubmissionStatus.Submitted);

				boolean allWorkSubmitted = !workList.isEmpty() && workList.stream()
						.allMatch(work -> work.getWorkSubmissionStatus() == DetailsSubmissionStatus.Submitted);
				// ________________________NEED TO CHECK_____________________

				if (allFamilySubmitted && allEducationSubmitted /* && allWorkSubmitted */
						&& agreementSubmissionStatus == DetailsSubmissionStatus.Submitted) {
					result = 1;
				}
			}

			if (result == 1) {
				Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);
				onboarding.setCandidatesStatus(CandidatesStatus.InReview);
				this.onboardingRepository.save(onboarding);
			}
		} catch (Exception e2) {
			// Handle the exception appropriately, e.g., log it or throw a custom exception
			e2.printStackTrace();
		}
		return result;
	}

}
