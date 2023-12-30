package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.CreateAppointmentLetterDto;
import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.payloads.AuthorizedSignDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrManagerAgreementApprovalDto;
import com.hrm.main.payloads.HrManagerBackgroundVerificationDto;
import com.hrm.main.payloads.HrManagerDto;
import com.hrm.main.payloads.HrManagerEducationApprovalDto;
import com.hrm.main.payloads.HrManagerFamilyApprovalDto;
import com.hrm.main.payloads.HrManagerPersonalApprovalDto;
import com.hrm.main.payloads.HrManagerWorkApprovalDto;

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

	String releaseAppointmentLetter(long candidateId, CreateAppointmentLetterDto appointmentLetterDto);

	EmployeeGenerateDto getReleaseAppointmentLetter(long candidateId);

	String createAppointmentLetter(CreateAppointmentLetterDto appointmentLetterDto, long candidateId);

}
