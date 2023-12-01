package com.hrm.main.servicesImpls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Agreement;
import com.hrm.main.models.Personal;
import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.main.payloads.AgreementDto;
import com.hrm.main.repositories.IAgreementRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.services.IAgreementService;

@Service
public class AgreementServiceImpl implements IAgreementService {

	@Autowired
	IAgreementRepository agreementRepository;
	@Autowired
	IPersonalRepository personalRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public String add(long candidateId, Agreement agreement) {
		Boolean existsByCandidateId = this.personalRepository.existsByCandidateId(candidateId);
		if (!existsByCandidateId) {
			try {
				Personal personal = this.personalRepository.findByCandidateId(candidateId);

				agreement.setEmployeeName((personal.getPersonalDetails().getFirstName()) + " "
						+ (personal.getPersonalDetails().getMiddleName()) + " "
						+ (personal.getPersonalDetails().getLastName()));
				agreement.setEmployeeAddress((personal.getAddressDetails().getPresentAdd().getHouseNo()) + ", "
						+ (personal.getAddressDetails().getPresentAdd().getArea()) + ", near "
						+ (personal.getAddressDetails().getPresentAdd().getLandmark()) + ", "
						+ (personal.getAddressDetails().getPresentAdd().getCity()) + ", "
						+ (personal.getAddressDetails().getPresentAdd().getState()) + ", "
						+ (personal.getAddressDetails().getPresentAdd().getPincode()));

				agreement.setPermanentAddress((personal.getAddressDetails().getPermanetAdd().getHouseNo()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getArea()) + ", near "
						+ (personal.getAddressDetails().getPermanetAdd().getLandmark()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getCity()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getState()) + ", "
						+ (personal.getAddressDetails().getPermanetAdd().getPincode()));

				agreement.setCandidateId(candidateId);
				agreement.setAgreementSubmissionStatus(DetailsSubmissionStatus.submitted);
				agreement.setHrExecutiveApprovalStatus(ApprovalStatus.Pending);
				var agreementAdded = this.agreementRepository.save(agreement);

				if (agreementAdded.getAgreementId() > 0) {
					return "Agreement are added : " + agreementAdded.getCandidateId();
				}
			} catch (Exception e) {
				e.getMessage();
			}
			return "Agreement are not added";
		}
		return "Agreement are already added";

	}

	/*
	 * @Override public String delete(String employeeId) {
	 * this.agreementRepository.deleteByEmployeeId(employeeId); return
	 * "Agreement of candidate Id " + employeeId + " is deleted Successfully"; }
	 */

	@Override
	public AgreementDto getByCandidateId(long candidateId) {
		Personal personal = this.personalRepository.findByCandidateId(candidateId);
		Agreement agreement = this.agreementRepository.findByCandidateId(candidateId);
		AgreementDto map = this.modelMapper.map(personal, AgreementDto.class);
		map.setCandidateId(personal.getCandidateId());
		map.setEmployerName(Agreement.getEmployerName());
		map.setCorporateOfficeAddress(Agreement.getCorporateOfficeAddress());
		map.setRegOfficeAddress(Agreement.getRegOfficeAddress());
		map.setAgreementId(agreement.getAgreementId());
		map.setEmployeeName(agreement.getEmployeeName());
		map.setNationality(agreement.getNationality());
		map.setEmployeeAddress(agreement.getEmployeeAddress());
		map.setAge(agreement.getAge());
		map.setPermanentAddress(agreement.getPermanentAddress());
		map.setDuration(agreement.getDuration());
		map.setStartDate(agreement.getStartDate());
		map.setEndDate(agreement.getEndDate());
		map.setLiquidatedDamagesAmount(agreement.getLiquidatedDamagesAmount());
		return map;
	}

}
