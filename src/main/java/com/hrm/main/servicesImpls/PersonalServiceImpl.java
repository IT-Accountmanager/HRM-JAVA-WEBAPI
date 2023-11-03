package com.hrm.main.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.BankDetails;
import com.hrm.main.models.DocumentDetails;
import com.hrm.main.models.DocumentUpload;
import com.hrm.main.models.Personal;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.main.payloads.PersonalStatusResponse;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.services.IPersonalService;

@Service
public class PersonalServiceImpl implements IPersonalService {

	@Autowired
	IPersonalRepository personalRepository;

	@Override
	public String addPersonal(Personal personal, long candidateId) {

		Boolean existsByCandidateId = this.personalRepository.existsByCandidateId(candidateId);
		if (!existsByCandidateId) {
			try {

				personal.setCandidateId(candidateId);
				// ----------------------------------------------------------
				Decoder decoder2 = Base64.getDecoder();
				while (personal.getPersonalDetails().base64Data.length() % 4 != 0) {
					personal.getPersonalDetails().base64Data += "=";
				}
				personal.getPersonalDetails()
						.setProfilePhoto(decoder2.decode(personal.getPersonalDetails().base64Data));
				personal.setPersonalSubmissionStatus(DetailsSubmissionStatus.submitted);

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

		return "Personal details are Already added";
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
			status = DetailsSubmissionStatus.pending;
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
