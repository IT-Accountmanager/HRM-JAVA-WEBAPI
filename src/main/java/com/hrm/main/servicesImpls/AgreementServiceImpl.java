package com.hrm.main.servicesImpls;

import java.time.LocalDate;
import java.time.Period;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Agreement;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.PermanentAddress;
import com.hrm.main.models.Personal;
import com.hrm.main.models.PresentAddress;
import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.main.payloads.AgreementDto;
import com.hrm.main.payloads.AgreementStatusResponse;
import com.hrm.main.repositories.IAgreementRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.services.IAgreementService;

@Service
public class AgreementServiceImpl implements IAgreementService {

	@Autowired
	IAgreementRepository agreementRepository;
	@Autowired
	IPersonalRepository personalRepository;
	@Autowired
	IOnboardingRepository onboardingRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public String add(long candidateId, Agreement agreement) {
		Boolean existsByCandidateId = this.agreementRepository.existsByCandidateId(candidateId);
		if (!existsByCandidateId) {
			try {
				Personal personal = this.personalRepository.findByCandidateId(candidateId);

				if (personal == null) {
					return "Personal details not found for candidate ID: " + candidateId;
				}

				agreement.setEmployeeName(String.format("%s %s %s", personal.getPersonalDetails().getFirstName(),
						personal.getPersonalDetails().getMiddleName(), personal.getPersonalDetails().getLastName()));

				agreement.setEmployeeFathersOrHusbandName(agreement.getEmployeeFathersOrHusbandName());

				agreement.setState(personal.getAddressDetails().getPresentAdd().getState());

				agreement.setPermanentAddress((personal.getAddressDetails().getPermanetAdd().getHouseNo()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getArea()) + ", near "
						+ (personal.getAddressDetails().getPermanetAdd().getLandmark()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getCity()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getState()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getPincode()));

				agreement.setCandidateId(candidateId);
				agreement.setAgreementSubmissionStatus(DetailsSubmissionStatus.Submitted);
				agreement.setHrExecutiveApprovalStatus(ApprovalStatus.Pending);

				Decoder decoder = Base64.getDecoder();
				while (agreement.signBase64.length() % 4 != 0) {
					agreement.signBase64 += "=";
				}
				agreement.setSign(decoder.decode(agreement.signBase64));

				while (agreement.leftHandThumbImpressionBase64.length() % 4 != 0) {
					agreement.leftHandThumbImpressionBase64 += "=";
				}
				agreement.setLeftHandThumbImpression(decoder.decode(agreement.leftHandThumbImpressionBase64));

				var agreementAdded = this.agreementRepository.save(agreement);

				if (agreementAdded.getAgreementId() > 0) {
					return "Agreement are added : " + agreementAdded.getCandidateId();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "Agreement are not added" + e.getMessage();

			}
		}
		return "Agreement are already added";

	}

	/*
	 * @Override public String add(long candidateId, Agreement agreement) { if
	 * (this.agreementRepository.existsByCandidateId(candidateId)) { return
	 * "Agreement is already added"; }
	 * 
	 * try { Personal personal =
	 * this.personalRepository.findByCandidateId(candidateId);
	 * 
	 * if (personal == null) { return
	 * "Personal details not found for candidate ID: " + candidateId; }
	 * 
	 * agreement.setEmployeeName(String.format("%s %s %s",
	 * personal.getPersonalDetails().getFirstName(),
	 * personal.getPersonalDetails().getMiddleName(),
	 * personal.getPersonalDetails().getLastName()));
	 * 
	 * agreement.setEmployeeFathersOrHusbandName(agreement.
	 * getEmployeeFathersOrHusbandName());
	 * 
	 * PresentAddress presentAddress = personal.getAddressDetails().getPresentAdd();
	 * if (presentAddress != null) { agreement.setState(presentAddress.getState());
	 * }
	 * 
	 * PermanentAddress permanentAddress =
	 * personal.getAddressDetails().getPermanetAdd(); if (permanentAddress != null)
	 * { agreement.setPermanentAddress(String.format("%s, %s, near %s, %s, %s, %s",
	 * permanentAddress.getHouseNo(), permanentAddress.getArea(),
	 * permanentAddress.getLandmark(), permanentAddress.getCity(),
	 * permanentAddress.getState(), permanentAddress.getPincode())); }
	 * 
	 * agreement.setCandidateId(candidateId);
	 * agreement.setAgreementSubmissionStatus(DetailsSubmissionStatus.Submitted);
	 * agreement.setHrExecutiveApprovalStatus(ApprovalStatus.Pending);
	 * 
	 * Decoder decoder = Base64.getDecoder();
	 * agreement.setSign(decodeBase64(agreement.signBase64));
	 * agreement.setLeftHandThumbImpression(decodeBase64(agreement.
	 * leftHandThumbImpressionBase64));
	 * 
	 * Agreement agreementAdded = this.agreementRepository.save(agreement);
	 * 
	 * if (agreementAdded.getAgreementId() > 0) { return
	 * "Agreement added for candidate ID: " + agreementAdded.getCandidateId(); } }
	 * catch (Exception e) { e.printStackTrace(); return "Error adding agreement: "
	 * + e.getMessage(); }
	 * 
	 * return "Agreement not added"; }
	 * 
	 * private byte[] decodeBase64(String base64String) { Decoder decoder =
	 * Base64.getDecoder(); while (base64String.length() % 4 != 0) { base64String +=
	 * "="; } return decoder.decode(base64String); }
	 */

	/*
	 * @Override public String delete(String employeeId) {
	 * this.agreementRepository.deleteByEmployeeId(employeeId); return
	 * "Agreement of candidate Id " + employeeId + " is deleted Successfully"; }
	 */

	@Override
	public Agreement getByCandidateId(long candidateId) {
		// Personal personal = this.personalRepository.findByCandidateId(candidateId);
		Agreement agreement = this.agreementRepository.findByCandidateId(candidateId);
		/*
		 * AgreementDto map = this.modelMapper.map(personal, AgreementDto.class);
		 * map.setCandidateId(candidateId);
		 * map.setAgreementDate(agreement.getAgreementDate());
		 * map.setEmployeeName(agreement.getEmployeeName());
		 * map.setEmployeeFathersOrHusbandName(agreement.getEmployeeFathersOrHusbandName
		 * ()); map.setAge(agreement.getAge());
		 * map.setReligion(agreement.getReligion()); map.setState(agreement.getState());
		 * map.setPermanentAddress(agreement.getPermanentAddress());
		 * map.setTenure(agreement.getTenure());
		 * map.setTenureFrom(agreement.getTenureFrom());
		 * map.setTenureTo(agreement.getTenureTo());
		 * map.setEmployerName(Agreement.getEmployerName());
		 * map.setCorporateOfficeAddress(Agreement.getCorporateOfficeAddress());
		 * map.setRegOfficeAddress(Agreement.getRegOfficeAddress());
		 * map.setDuration(agreement.getDuration());
		 * map.setStartDate(agreement.getStartDate());
		 * map.setEndDate(agreement.getEndDate());
		 * map.setServiceBreakAmount(agreement.getServiceBreakAmount());
		 * map.setCustodyOf(agreement.getCustodyOf());
		 */

		return agreement;
	}

	@Override
	public AgreementStatusResponse getStatusByCandidateId(long candidateId) {
		DetailsSubmissionStatus status;
		Agreement agreement = this.agreementRepository.findByCandidateId(candidateId);

		if (agreement == null) {
			status = DetailsSubmissionStatus.Pending;
		} else {
			status = agreement.getAgreementSubmissionStatus();
		}
		return new AgreementStatusResponse(status);
	}

	@Override
	public AgreementDto getPreAgreementInfo(long candidateId) {
		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);
		Personal personal = this.personalRepository.findByCandidateId(candidateId);
		AgreementDto preAgreementInfo = new AgreementDto();
		preAgreementInfo.setAgreementDate(LocalDate.now());
		preAgreementInfo.setEmployeeName(onboarding.getCandidateName());
		preAgreementInfo.setEmployeeFathersOrHusbandName(null);
		preAgreementInfo
				.setAge(Period.between(personal.getPersonalDetails().getDateOfBirth(), LocalDate.now()).getYears());
		preAgreementInfo.setReligion("Hindu");
		preAgreementInfo.setState(personal.getAddressDetails().getPermanetAdd().getState());
		preAgreementInfo.setPermanentAddress((personal.getAddressDetails().getPermanetAdd().getHouseNo()) + ", "
				+ (personal.getAddressDetails().getPermanetAdd().getArea()) + ", near "
				+ (personal.getAddressDetails().getPermanetAdd().getLandmark()) + ", "
				+ (personal.getAddressDetails().getPermanetAdd().getCity()) + ", "
				+ (personal.getAddressDetails().getPermanetAdd().getState()) + ", "
				+ (personal.getAddressDetails().getPermanetAdd().getPincode()));
		preAgreementInfo.setServiceCommitment(onboarding.getServiceCommitment());
		preAgreementInfo.setTenureFrom(LocalDate.now());
		int months = (int) onboarding.getServiceCommitment() * 12;

		preAgreementInfo.setTenureTo(LocalDate.now().plusMonths(months));
		preAgreementInfo.setServiceBreakAmount(onboarding.getServiceBreakAmount());
		preAgreementInfo.setCustodyOf("HR");

		return preAgreementInfo;
	}

}
