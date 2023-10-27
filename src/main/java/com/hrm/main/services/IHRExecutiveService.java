package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.main.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrExecutiveWorkApprovalDto;

public interface IHRExecutiveService {

	String createExecutive(HRExecutive hrExecutive);

	List<HRExecutive> getAllExecutive();

	HRExecutive getExecutiveById(int id);

	String updateHRExecutive(HRExecutive hrExecutive, Integer id);

	String deleteHrExecutive(Integer id);

	// String tranferProfileToHRExecutive();

	// List<Onboarding> getAllOnboarding(Status ststus);

	boolean postCandidateInHrExecutive(CandidatesStatus status);

	HrExecutivePersonalApprovalDto personalApproval(HrExecutivePersonalApprovalDto hrExecutivePersonalDto,
			long candidateId);

	HrExecutiveEducationApprovalDto educationApproval(HrExecutiveEducationApprovalDto hrExecutiveEducationDto,
			long candidateId);

	HrExecutiveWorkApprovalDto workApproval(HrExecutiveWorkApprovalDto hrExecutiveWorkDto, long candidateId);

	HrExecutiveFamilyApprovalDto familyApproval(HrExecutiveFamilyApprovalDto hrExecutiveFamilyDto, long candidateId);

	// List<Onboarding> getAllOnboarding();

}
