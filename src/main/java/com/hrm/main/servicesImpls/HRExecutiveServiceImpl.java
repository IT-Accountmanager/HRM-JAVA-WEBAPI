package com.hrm.main.servicesImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.repositories.IHRExecutiveRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.services.IHRExecutiveService;

@Service

public class HRExecutiveServiceImpl implements IHRExecutiveService {

	@Autowired
	private IHRExecutiveRepository hRExecutiveRepository;

	@Autowired
	private IPersonalRepository iPersonalRepository;

	@Autowired
	private IOnboardingRepository iOnboardingRepository;

	@Override
	public String createExecutive(HRExecutive hrExecutive) {
		try {
			// super()

			var executive1 = this.hRExecutiveRepository.save(hrExecutive);
			if (executive1.getId() > 0) {
				return "All details are added : " + executive1.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "All details are not added : ";
	}

	@Override
	public List<HRExecutive> getAllExecutive() {
		List<HRExecutive> allExecutive = hRExecutiveRepository.findAll();
		return allExecutive;
	}

	@Override
	public HRExecutive getExecutiveById(int id) {
		HRExecutive hr = hRExecutiveRepository.findById(id).get();
		return hr;
	}

	@Override
	public String updateHRExecutive(HRExecutive hrExecutive, Integer id) {
		try {

			if (this.hRExecutiveRepository.existsById(id)) {

				hrExecutive.setId(id);

				this.hRExecutiveRepository.save(hrExecutive);

				return "Id no. : " + id + " is updated.";

			} else {
				return "Id no. : " + id + " is does not exists";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. : " + id + "is not updated.";
	}

	@Override
	public String deleteHrExecutive(Integer id) {
		try {
			hRExecutiveRepository.deleteById(id);

			return "Id no. : " + id + " is deleted";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. : " + id + " is not deleted";
	}

	@Override
	public String tranferProfileToHRExecutive() {/*
													 * List<Onboarding> findAll = this.iOnboardingRepository.findAll();
													 * List<HRExecutive> listHrExecutive = new ArrayList<>();
													 * 
													 * for (Onboarding onboarding : findAll) { int candidateId =
													 * onboarding.getCandidateId(); List<Personal> candidates =
													 * this.iPersonalRepository.findByCandidateId(candidateId);
													 * 
													 * // Check if there are multiple candidates with the same
													 * candidateId. if (candidates.size() > 1) { // Handle the case
													 * where multiple candidates have the same candidateId. // You can
													 * log an error or decide how to handle this situation. // For now,
													 * we'll skip the candidate. continue; }
													 * 
													 * // Check if there is a candidate with the given candidateId. if
													 * (!candidates.isEmpty()) { Personal candidate = candidates.get(0);
													 * // Assuming candidateId is unique. HRExecutive transfer = new
													 * HRExecutive();
													 * 
													 * transfer.setJobTitle(onboarding.getJobTitle());
													 * transfer.setCandidateId(candidateId);
													 * transfer.setCandidateName(onboarding.getCandidateName());
													 * transfer.setContactNumber(onboarding.getContactNumber());
													 * transfer.setEmailId(onboarding.getEmailId());
													 * transfer.setBondPeriod(onboarding.getBondPeriod());
													 * transfer.setBondBreakAmount(onboarding.getBondBreakAmount());
													 * transfer.setCtc(onboarding.getCtc());
													 * 
													 * listHrExecutive.add(transfer); } }
													 * 
													 * if (!listHrExecutive.isEmpty()) {
													 * this.hRExecutiveRepository.saveAll(listHrExecutive); return
													 * "Transferred to HR Executive Successfully"; }
													 * 
													 * return "Not Transferred to HR Executive";
													 * 
													 * 
													 */

		// ---------------------------------------------------------------

		List<Onboarding> findAll = this.iOnboardingRepository.findAll();

		List<HRExecutive> listHrExecutive = new ArrayList<>();
		// System.out.println("----Find All----" + findAll);

		for (Onboarding onboarding : findAll) {
			//
			System.out.println("----Onboarding----" + onboarding);
			int candidateId = onboarding.getCandidateId();
			// System.out.println("----Candidate Id----" +
			// candidateId);

			// System.out.println("----Print----" + //
			// this.iPersonalRepository.findByCandidateId(candidateId));

			Personal candidate = this.iPersonalRepository.findByCandidateId(candidateId);
			System.out.println("----candidate----" + candidate);
			if (candidate != null && candidate.getPersonalId() != 0) {

				HRExecutive transfer = new HRExecutive();

				transfer.setJobTitle(onboarding.getJobTitle());
				transfer.setCandidateId(candidateId);
				transfer.setCandidateName(onboarding.getCandidateName());
				transfer.setContactNumber(onboarding.getContactNumber());
				transfer.setEmailId(onboarding.getEmailId());
				transfer.setBondPeriod(onboarding.getBondPeriod());
				transfer.setBondBreakAmount(onboarding.getBondBreakAmount());
				transfer.setCtc(onboarding.getCtc()); //
				// transfer.setStatus(onboarding.getStatus());

				listHrExecutive.add(transfer);
			}
		}

		if (!listHrExecutive.isEmpty()) {
			this.hRExecutiveRepository.saveAll(listHrExecutive);
			return "Transfered to HR Executive Succesfully";
		}

		/*
		 * Personal personal = this.iPersonalRepository.findById(id).get(); if
		 * (!(personal.equals(null))) { HRExecutive transfer = new HRExecutive();
		 * 
		 * transfer.setCandidateId(personal.getCandidateId());
		 * transfer.setJobTitle(personal.g); this.hRExecutiveRepository.save(transfer);
		 * } return null;
		 */

		return "Not Transfered to HR Executive";
	}

}
