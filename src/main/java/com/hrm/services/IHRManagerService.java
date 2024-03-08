package com.hrm.services;

import java.util.List;

import com.hrm.helper.CtcBreakup;
import com.hrm.helper.PDF;
import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.models.HRManager;
import com.hrm.models.Onboarding;
import com.hrm.payloads.AuthorizedSignDto;
import com.hrm.payloads.CreateAppointmentLetterDto;
import com.hrm.payloads.EmployeeGenerateDto;
import com.hrm.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.payloads.HrManagerAgreementApprovalDto;
import com.hrm.payloads.HrManagerBackgroundVerificationDto;
import com.hrm.payloads.HrManagerDto;
import com.hrm.payloads.HrManagerEducationApprovalDto;
import com.hrm.payloads.HrManagerFamilyApprovalDto;
import com.hrm.payloads.HrManagerPersonalApprovalDto;
import com.hrm.payloads.HrManagerWorkApprovalDto;
import com.hrm.payloads.ReleaseAppointmentLetterDto;

public interface IHRManagerService {

	boolean postCandidatesInHrManager(CandidatesStatus status);

	HrManagerDto getCandidate(long candidateId);

	String updateHRManager(HRManager hrManager, Integer id);

	String deleteHRManager(Integer id);

	Long nextValue();

	List<HrManagerDto> getAllCandidates(CandidatesStatus status);

	HrManagerPersonalApprovalDto personalApproval(HrManagerPersonalApprovalDto hrManagerPersonalApprovalDto,
			long candidateId);

	HrManagerPersonalApprovalDto getPersonalApproval(long candidateId);

	HrManagerFamilyApprovalDto familyApproval(HrManagerFamilyApprovalDto hrManagerFamilyApprovalDto, long candidateId);

	HrManagerFamilyApprovalDto getFamilyApproval(long candidateId);

	HrManagerEducationApprovalDto educationApproval(HrManagerEducationApprovalDto hrManagerEducationApprovalDto,
			long candidateId);

	HrManagerEducationApprovalDto getEducationApproval(long candidateId);

	int workApproval(HrManagerWorkApprovalDto hrManagerWorkApprovalDto, long candidateId);

	HrManagerWorkApprovalDto getWorkApproval(long candidateId);

	Integer rejectHrManager(long candiateId);

	EmployeeGenerateDto getAppointmentLetter(long candidateId);

	HrManagerAgreementApprovalDto agreementApproval(HrManagerAgreementApprovalDto hrManagerAgreementApprovalDto,
			long candidateId);

	HrManagerAgreementApprovalDto getAgreementApproval(long candidateId);

	// String addAuthorizedSign(AuthorizedSignDto authorizedSign, long candidateId);

	String bgvApproval(HrManagerBackgroundVerificationDto bgv, long candidateId);

	HrManagerBackgroundVerificationDto getBgvApproval(long candidateId);

	String editAppointment(EmployeeGenerateDto appointmentInfo, long candidateId);

	ReleaseAppointmentLetterDto releaseAppointmentLetter(long candidateId,
			CreateAppointmentLetterDto appointmentLetterDto);

	EmployeeGenerateDto getReleaseAppointmentLetter(long candidateId);

	String createAppointmentLetter(CreateAppointmentLetterDto appointmentLetterDto, long candidateId);

	CtcBreakup check(long candidateId);

}
