package com.hrm.services;

import java.util.List;

import com.hrm.Helper.EnumCollection.CandidatesStatus;
import com.hrm.models.BackgroundVerification;
import com.hrm.models.HRExecutive;
import com.hrm.models.Onboarding;
import com.hrm.payloads.HrExecutiveAgreementApprovalDto;
import com.hrm.payloads.HrExecutiveBgvSubmissionDto;
import com.hrm.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.payloads.HrExecutiveWorkApprovalDto;

public interface IHRExecutiveService {

	String createExecutive(HRExecutive hrExecutive);

	List<Onboarding> getAllExecutive(CandidatesStatus inReview, CandidatesStatus hRManagerRejected);

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

	Onboarding getByCandidateId(long candidateId);

	HrExecutivePersonalApprovalDto getPersonalApproval(long candidateId);

	HrExecutiveFamilyApprovalDto getFamilyApproval(long candidateId);

	HrExecutiveEducationApprovalDto getEducationApproval(long candidateId);

	HrExecutiveWorkApprovalDto getWorkApproval(long candidateId);

	Integer submitHrExecutive(long candiateId);

	Integer rejectHrExecutive(long candiateId);

	HrExecutiveAgreementApprovalDto agreementApproval(HrExecutiveAgreementApprovalDto hrExecutiveAgreementApprovalDto,
			long candidateId);

	HrExecutiveAgreementApprovalDto getAgreementApproval(long candidateId);

	String postBgv(BackgroundVerification bgv, long candidateId);

	HrExecutiveBgvSubmissionDto getBgvApproval(long candidateId);

	BackgroundVerification getBgv(long candidateId);

	// List<Onboarding> getAllOnboarding();

}
