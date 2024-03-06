package com.hrm.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.models.Education;
import com.hrm.models.Family;
import com.hrm.payloads.EducationStatusResponse;
import com.hrm.payloads.FamilyStatusResponse;
import com.hrm.repositories.IEducationRepository;
import com.hrm.services.IEducationService;

@Service
public class EducationServiceImpl implements IEducationService {

	@Autowired
	private IEducationRepository educationRepo;

	@Override
	public String createEducation(Education education, long candidateId) {
		try {
			education.setCandidateId(candidateId);
			education.setEducationSubmissionStatus(DetailsSubmissionStatus.Submitted);
			Decoder decoder = Base64.getDecoder();

			while (education.base64Data.length() % 4 != 0) {
				education.base64Data += "=";
			}
			education.setCertificate(decoder.decode(education.base64Data));

			var edu = this.educationRepo.save(education);
			if (edu.getEducationId() > 0) {
				return "Education details are added : " + edu.getEducationId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Education details are not added";

	}

	@Override
	public List<Education> getAllEducationByCandidateId(long candidateId) {
		List<Education> allEdu = educationRepo.findAllByCandidateId(candidateId);
		return allEdu;
	}

	@Override
	public Education getEducation(Integer id) {
		Education edu = educationRepo.findById(id).get();
		return edu;
	}

	@Override
	public String deleteEducation(Integer id) {
		try {

			educationRepo.deleteById(id);
			return "Id no. " + id + " is Deleted Succeefully.";
		} catch (Exception e) {
			e.getMessage();
		}

		return "Id no. " + id + " is not Deleted.";
	}

	@Override
	public String updateEducation(Education edu, Integer id) {

		try {
			if (this.educationRepo.existsById(id)) {

				edu.setEducationId(id);
				edu.setEducationSubmissionStatus(DetailsSubmissionStatus.Submitted);

				Decoder decoder = Base64.getDecoder();

				while (edu.base64Data.length() % 4 != 0) {
					edu.base64Data += "=";
				}
				edu.setCertificate(decoder.decode(edu.base64Data));
				this.educationRepo.save(edu);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	/*
	 * @Override public EducationStatusResponse getEducationStatusByCandidateId(long
	 * candidateId) { DetailsSubmissionStatus status;
	 * 
	 * Education education = this.educationRepo.findByCandidateId(candidateId); if
	 * (education != null) { return new
	 * EducationStatusResponse(DetailsSubmissionStatus.submitted); } return new
	 * EducationStatusResponse(DetailsSubmissionStatus.pending); }
	 */

	@Override
	public EducationStatusResponse getEducationStatusByCandidateId(long candidateId) {
		List<Education> education = this.educationRepo.findAllByCandidateId(candidateId);

		if (education != null && !education.isEmpty()) {
			boolean allSubmitted = education.stream()
					.allMatch(f -> f.getEducationSubmissionStatus() == DetailsSubmissionStatus.Submitted);

			if (allSubmitted) {
				return new EducationStatusResponse(DetailsSubmissionStatus.Submitted);
			}
		}

		return new EducationStatusResponse(DetailsSubmissionStatus.Pending);
	}

}
