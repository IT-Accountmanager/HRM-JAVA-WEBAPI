
package com.hrm.servicesImpls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.Helper.EnumCollection.CandidatesStatus;
import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.Helper.EnumCollection.HrSubmission;
import com.hrm.models.Agreement;
import com.hrm.models.Education;
import com.hrm.models.Family;
import com.hrm.models.Onboarding;
import com.hrm.models.Personal;
import com.hrm.models.PersonalDetails;
import com.hrm.models.Profile;
import com.hrm.models.Work;
import com.hrm.payloads.ProfileSummaryDto;
import com.hrm.repositories.IAgreementRepository;
import com.hrm.repositories.IEducationRepository;
import com.hrm.repositories.IFamilyRepository;
import com.hrm.repositories.IOnboardingRepository;
import com.hrm.repositories.IPersonalRepository;
import com.hrm.repositories.IProfileRepository;
import com.hrm.repositories.IWorkRepository;
import com.hrm.services.IProfileService;

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
	public List<Onboarding> getPendingOnboardings() {

		List<Onboarding> findAll = this.onboardingRepository.findAll();
		List<Onboarding> listForProfile = new ArrayList<>();

		for (Onboarding onboarding : findAll) {

			HrSubmission submissionStatus = onboarding.getHrExecutiveSubmission();

			if (submissionStatus == HrSubmission.Pending || submissionStatus == HrSubmission.Reject) {
				listForProfile.add(onboarding);
			}
		}

		return listForProfile;
	}

	@Override
	public Onboarding getOnboardingByCandidateId(long candidateId) {
		return this.onboardingRepository.findByCandidateId(candidateId);
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
