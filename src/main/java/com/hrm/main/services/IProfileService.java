
package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Profile;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import com.hrm.main.payloads.ProfileSummaryDto;

public interface IProfileService {

	/* String changeStatus(Integer id, String status); */

	List<Onboarding> getPendingOnboardings();

	Onboarding getOnboardingByCandidateId(long candidateId);

	int createProfile(Profile profile);

	int createPersonalDetails(PersonalDetails personalDetails);

	int updatingCandidatesStatus(long candidateId);
}
