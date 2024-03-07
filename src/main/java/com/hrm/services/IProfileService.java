
package com.hrm.services;

import java.util.List;

import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.helper.EnumCollection.HrSubmission;
import com.hrm.models.Onboarding;
import com.hrm.models.PersonalDetails;
import com.hrm.models.Profile;
import com.hrm.payloads.ProfileSummaryDto;

public interface IProfileService {

	/* String changeStatus(Integer id, String status); */

	List<Onboarding> getPendingOnboardings();

	Onboarding getOnboardingByCandidateId(long candidateId);

	int createProfile(Profile profile);

	int createPersonalDetails(PersonalDetails personalDetails);

	int updatingCandidatesStatus(long candidateId);
}
