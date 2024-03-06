package com.hrm.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.models.Onboarding;
import com.hrm.models.Personal;
import com.hrm.payloads.PersonalStatusResponse;
import com.hrm.repositories.IOnboardingRepository;
import com.hrm.repositories.IPersonalRepository;
import com.hrm.services.IPersonalService;

@Service
public class PersonalServiceImpl implements IPersonalService {

	@Autowired
	IPersonalRepository personalRepository;

	@Autowired
	IOnboardingRepository onboardingRepository;

	@Override
	public String addPersonal(Personal personal, long candidateId) {

		Boolean existsByCandidateId = this.personalRepository.existsByCandidateId(candidateId);
		if (!existsByCandidateId) {
			try {

				personal.setCandidateId(candidateId);

				Onboarding onboardingcandidate = onboardingRepository.findByCandidateId(candidateId);
				onboardingcandidate.setCandidateName(personal.getPersonalDetails().getFirstName() + " "
						+ personal.getPersonalDetails().getMiddleName() + " "
						+ personal.getPersonalDetails().getLastName());

				// ----------------------------------------------------------
				Decoder decoder2 = Base64.getDecoder();
				while (personal.getPersonalDetails().base64Data.length() % 4 != 0) {
					personal.getPersonalDetails().base64Data += "=";
				}
				personal.getPersonalDetails()
						.setProfilePhoto(decoder2.decode(personal.getPersonalDetails().base64Data));
				personal.setPersonalSubmissionStatus(DetailsSubmissionStatus.Submitted);

				// ---------------------------------------------------------

				Decoder decoder = Base64.getDecoder();
				while (personal.getBankDetails().base64Data.length() % 4 != 0) {
					personal.getBankDetails().base64Data += "=";
				}
				personal.getBankDetails().setBankDoc(decoder.decode(personal.getBankDetails().base64Data));

				// ----------------------------------------------------------
				if (!personal.getDocumentDetails().getDocuUpload().isEmpty()
						&& personal.getDocumentDetails().getDocuUpload().size() > 0) {
					personal.getDocumentDetails().getDocuUpload().forEach(x -> {

						Decoder decoder1 = Base64.getDecoder();
						while (x.base64Data.length() % 4 != 0) {
							x.base64Data += "=";
						}
						x.setContent(decoder1.decode(x.base64Data));
					});
				}

				// -----------------------------------------------------------

				Personal info = this.personalRepository.save(personal);
				if (info.getPersonalId() > 0) {
					return "Personal details are added : " + info.getPersonalId();
				}
			} catch (Exception e) {
				e.getMessage();
			}

		}
		if (existsByCandidateId) {
			try {
				Personal existingPersonal = personalRepository.findByCandidateId(candidateId);

				Decoder decoder2 = Base64.getDecoder();
				while (personal.getPersonalDetails().base64Data.length() % 4 != 0) {
					personal.getPersonalDetails().base64Data += "=";
				}
				personal.getPersonalDetails()
						.setProfilePhoto(decoder2.decode(personal.getPersonalDetails().base64Data));

				Decoder decoder = Base64.getDecoder();
				while (personal.getBankDetails().base64Data.length() % 4 != 0) {
					personal.getBankDetails().base64Data += "=";
				}
				personal.getBankDetails().setBankDoc(decoder.decode(personal.getBankDetails().base64Data));

				if (!personal.getDocumentDetails().getDocuUpload().isEmpty()
						&& personal.getDocumentDetails().getDocuUpload().size() > 0) {
					personal.getDocumentDetails().getDocuUpload().forEach(x -> {

						Decoder decoder1 = Base64.getDecoder();
						while (x.base64Data.length() % 4 != 0) {
							x.base64Data += "=";
						}
						x.setContent(decoder1.decode(x.base64Data));
					});
				}

				existingPersonal.updateFrom(personal);

				personalRepository.save(existingPersonal);

				return "Personal details updated successfully for candidateId: " + candidateId;
			} catch (Exception e) {
				e.printStackTrace();
				return "Error updating personal details";
			}
		} else {
			return "Personal details not found for candidateId: " + candidateId;
		}

	}

	@Override
	public Personal getPersonalDetailsByCandidateId(long candidateId) {
		Personal findByCandidateId = this.personalRepository.findByCandidateId(candidateId);
		return findByCandidateId;
	}

	@Override
	public Personal getPersonalById(Integer id) {
		Personal personal = this.personalRepository.findById(id).get();
		// personal.getPersonalDetails().setProfilePhoto((org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(personal.getPersonalDetails().getProfilePhoto())));
		/*
		 * personal.getPersonalDetails().base64Data =
		 * org.apache.tomcat.util.codec.binary.Base64
		 * .encodeBase64String(personal.getPersonalDetails().getProfilePhoto());
		 * System.out.println("----Base64---- = " +
		 * personal.getPersonalDetails().base64Data);
		 */
		return personal;
	}

	@Override
	public PersonalStatusResponse getStatusByCandidateId(long candidateId) {
		DetailsSubmissionStatus status;
		Personal personal = this.personalRepository.findByCandidateId(candidateId);

		if (personal == null) {
			status = DetailsSubmissionStatus.Pending;
		} else {
			status = personal.getPersonalSubmissionStatus();
		}
		return new PersonalStatusResponse(status);
	}

	/*
	 * @Override public Personal getStatusByCandidateId(String candidateId) {
	 * Personal findPersonalSubmissionStatusByCandidateId = this.personalRepository
	 * .findPersonalSubmissionStatusByCandidateId(candidateId); return
	 * findPersonalSubmissionStatusByCandidateId; }
	 */
}
