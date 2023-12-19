package com.hrm.main.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.main.payloads.WorkStatusResponse;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.IWorkService;

@Service
public class WorkServiceImpl implements IWorkService {

	@Autowired
	private IWorkRepository workRepo;

	@Override
	public String createWork(Work work, long candidateId) {
		try {
			work.setCandidateId(candidateId);
			work.setWorkSubmissionStatus(DetailsSubmissionStatus.Submitted);
			work.setHrExecutiveApprovalStatus(ApprovalStatus.Pending);
			Decoder decoder = Base64.getDecoder();
			while (work.paySlipBase64Data.length() % 4 != 0) {
				work.paySlipBase64Data += "=";
			}
			while (work.appraisalLetterBase64Data.length() % 4 != 0) {
				work.appraisalLetterBase64Data += "=";
			}
			/*
			 * while (work.relievedLetterBase64Data.length() % 4 != 0) {
			 * work.relievedLetterBase64Data += "="; }
			 */

			// work.setUploadOfferLetter(decoder.decode(work.offerLetterBase64Data));
			work.setLastAppraisalLetter(decoder.decode(work.appraisalLetterBase64Data));
			work.setLastMonthPaySlip(decoder.decode(work.paySlipBase64Data));

			var work1 = this.workRepo.save(work);
			if (work1.getWorkId() > 0) {
				return "Work details are added : " + work1.getWorkId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "work details are not added : ";

	}

	@Override
	public List<Work> getAllWorkByCandidateId(long candidateId) {
		List<Work> allWork = workRepo.findAllWorkByCandidateId(candidateId);
		return allWork;
	}

	@Override
	public Work getWork(Integer id) {
		Work findById = workRepo.findById(id).get();

		return findById;
	}

	@Override
	public String updateWork(Work work, Integer id) {

		try {
			Decoder decoder = Base64.getDecoder();
			while (work.paySlipBase64Data.length() % 4 != 0) {
				work.paySlipBase64Data += "=";
			}
			while (work.appraisalLetterBase64Data.length() % 4 != 0) {
				work.appraisalLetterBase64Data += "=";
			}
			/*
			 * while (work.relievedLetterBase64Data.length() % 4 != 0) {
			 * work.relievedLetterBase64Data += "="; }
			 */

			// work.setUploadOfferLetter(decoder.decode(work.offerLetterBase64Data));
			work.setLastAppraisalLetter(decoder.decode(work.appraisalLetterBase64Data));
			work.setLastMonthPaySlip(decoder.decode(work.paySlipBase64Data));
			work.setWorkId(id);

			workRepo.save(work);

			return "Id no. " + id + " Is Updated.";

		} catch (Exception ex) {
			ex.getMessage();
		}

		return "Id no. " + id + " Is not Updated.";

	}

	@Override
	public String deleteWork(Integer id) {
		try {
			workRepo.deleteById(id);
			return "Id no. " + id + " is Deleted Succeefully.";
		} catch (Exception e) {
			e.getMessage();
		}

		return "Id no. " + id + " is not Deleted.";
	}

	@Override
	public String getDocument(Integer id) {
		byte[] appraisalLetterBase64Data = workRepo.findById(id).get().getLastAppraisalLetter();

		return org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(appraisalLetterBase64Data);
	}

	/*
	 * @Override public WorkStatusResponse getWorkStatusByCandidateId(long
	 * candidateId) { Work work = this.workRepo.findByCandidateId(candidateId); if
	 * (work != null) { return new
	 * WorkStatusResponse(DetailsSubmissionStatus.submitted); } return new
	 * WorkStatusResponse(DetailsSubmissionStatus.pending); }
	 */

	@Override
	public WorkStatusResponse getWorkStatusByCandidateId(long candidateId) {
		List<Work> work = this.workRepo.findAllWorkByCandidateId(candidateId);

		if (work != null && !work.isEmpty()) {
			boolean allSubmitted = work.stream()
					.allMatch(f -> f.getWorkSubmissionStatus() == DetailsSubmissionStatus.Submitted);

			if (allSubmitted) {
				return new WorkStatusResponse(DetailsSubmissionStatus.Submitted);
			}
		}

		return new WorkStatusResponse(DetailsSubmissionStatus.Pending);
	}

}
