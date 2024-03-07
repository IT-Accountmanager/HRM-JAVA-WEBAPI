package com.hrm.servicesImpls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.models.Family;
import com.hrm.payloads.FamilyStatusResponse;
import com.hrm.repositories.IFamilyRepository;
import com.hrm.services.IFamilyService;

@Service
public class FamilyServiceImpl implements IFamilyService {

	@Autowired
	private IFamilyRepository familyRepo;

	@Override
	public String createFamily(Family family, long candidateId) {
		try {
			if (familyRepo.existsByPhoneNumber(family.getPhoneNumber())) {
				return "Duplicate mobile number. Family details are not added";
			}
			family.setCandidateId(candidateId);
			family.setFamilySubmissionStatus(DetailsSubmissionStatus.Submitted);
			family.setHrExecutiveApprovalStatus(ApprovalStatus.Pending);
			var fam = this.familyRepo.save(family);

			if (fam.getFamilyId() > 0) {
				return "Family details are added : " + fam.getFamilyId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Family details are not added";
	}

	@Override
	public List<Family> getAllFamilyByCandidateId(long candidateId) {
		List<Family> allFamily = this.familyRepo.findAllByCandidateId(candidateId);
		return allFamily;
	}

	@Override
	public Family getFamilyById(int id) {

		Family family = familyRepo.findById(id).get();
		return family;
	}

	@Override
	public String updateFamily(Family family, Integer id) {

		try {
			if (this.familyRepo.existsById(id)) {
				family.setFamilyId(id);
				family.setFamilySubmissionStatus(DetailsSubmissionStatus.Submitted);
				family.setHrExecutiveApprovalStatus(ApprovalStatus.Pending);
				Family save = this.familyRepo.save(family);
				return "Id no. " + save.getCandidateId() + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public String deleteFamily(Integer family_id) {
		try {
			familyRepo.deleteByFamilyId(family_id);

			return "Id no. " + family_id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + family_id + " is not deleted. ";
	}

	@Override
	public FamilyStatusResponse getFamilyStatusByCandidateId(long candidateId) {
		List<Family> family = this.familyRepo.findAllByCandidateId(candidateId);

		if (family != null && !family.isEmpty()) {
			boolean allSubmitted = family.stream()
					.allMatch(f -> f.getFamilySubmissionStatus() == DetailsSubmissionStatus.Submitted);

			if (allSubmitted) {
				return new FamilyStatusResponse(DetailsSubmissionStatus.Submitted);
			}
		}

		return new FamilyStatusResponse(DetailsSubmissionStatus.Pending);
	}

}
