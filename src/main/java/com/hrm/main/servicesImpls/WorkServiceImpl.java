package com.hrm.main.servicesImpls;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Work;
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
			work.setWorkSubmissionStatus(DetailsSubmissionStatus.submitted);
			Decoder decoder = Base64.getDecoder();
			while (work.offerLetterBase64Data.length() % 4 != 0) {
				work.offerLetterBase64Data += "=";
			}
			while (work.appraisalLetterBase64Data.length() % 4 != 0) {
				work.appraisalLetterBase64Data += "=";
			}
			while (work.relievedLetterBase64Data.length() % 4 != 0) {
				work.relievedLetterBase64Data += "=";
			}

			work.setUploadOfferLetter(decoder.decode(work.offerLetterBase64Data));
			work.setAppraisalLetter(decoder.decode(work.appraisalLetterBase64Data));
			work.setRelievedLetter(decoder.decode(work.relievedLetterBase64Data));

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
			while (work.offerLetterBase64Data.length() % 4 != 0) {
				work.offerLetterBase64Data += "=";
			}
			while (work.appraisalLetterBase64Data.length() % 4 != 0) {
				work.appraisalLetterBase64Data += "=";
			}
			while (work.relievedLetterBase64Data.length() % 4 != 0) {
				work.relievedLetterBase64Data += "=";
			}

			work.setUploadOfferLetter(decoder.decode(work.offerLetterBase64Data));
			work.setAppraisalLetter(decoder.decode(work.appraisalLetterBase64Data));
			work.setRelievedLetter(decoder.decode(work.relievedLetterBase64Data));
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
		byte[] relievedLetterBase64Data = workRepo.findById(id).get().getRelievedLetter();

		return org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(relievedLetterBase64Data);
	}

	@Override
	public WorkStatusResponse getWorkStatusByCandidateId(long candidateId) {
		Work work = this.workRepo.findByCandidateId(candidateId);
		if (work != null) {
			return new WorkStatusResponse(DetailsSubmissionStatus.submitted);
		}
		return new WorkStatusResponse(DetailsSubmissionStatus.pending);
	}

}
