package com.hrm.main.servicesImpls;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.repositories.IHRManagerRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.services.IHRManagerService;

@Service

public class HRManagerServiceImpl implements IHRManagerService {

	@Autowired
	private IHRManagerRepository hRManagerRepository;
	@Autowired
	private IOnboardingRepository onboardingRepository;

	@Override
	public boolean postCandidateInHrManager(CandidatesStatus status) {
		List<Onboarding> findAllByCandidatesStatus = this.onboardingRepository.findAllByCandidatesStatus(status);
		boolean verify = false;

		List<HRManager> candidatesHRManager = findAllByCandidatesStatus.stream().filter(onboarding -> {
			HRManager existingCandidates = this.hRManagerRepository.findByCandidateId(onboarding.getCandidateId());
			return existingCandidates == null;
		})

				/*
				 * List<HRExecutive> hrExecutiveList = findAllByCandidatesStatus.stream()
				 * .filter(onboarding -> { HRExecutive existingExecutive =
				 * this.hRExecutiveRepository.findByCandidateId(onboarding.getCandidateId());
				 * return existingExecutive == null; })
				 */

				.map(onboarding -> {
					HRManager hrManager = new HRManager();
					hrManager.setJobTitle(onboarding.getJobTitle());
					hrManager.setCandidateId(onboarding.getCandidateId());
					hrManager.setCandidateName(onboarding.getCandidateName());
					hrManager.setContactNumber(onboarding.getContactNumber());
					hrManager.setEmailId(onboarding.getEmailId());
					hrManager.setBondPeriod(onboarding.getBondPeriod());
					hrManager.setBondBreakAmount(onboarding.getBondBreakAmount());
					hrManager.setCtc(onboarding.getCtc());
					hrManager.setStatus(onboarding.getCandidatesStatus());

					return hrManager;

				}).collect(Collectors.toList());

		List<HRManager> success = this.hRManagerRepository.saveAll(candidatesHRManager);

		return !success.isEmpty();

		/*
		 * try { HRManager manager = this.hRManagerRepository.findByCandidateId(status);
		 * if (manager.getId() > 0) { return "All detail are added : " +
		 * manager.getId(); } } catch (Exception e) { e.getMessage(); } return
		 * "All details are not added";
		 */ }

	@Override
	public List<Onboarding> getAllHRManager(CandidatesStatus status) {
		List<Onboarding> allmanager = this.onboardingRepository.findAllByCandidatesStatus(status);
		return allmanager;
	}

	@Override
	public HRManager getHRManager(Integer id) {
		HRManager manager = hRManagerRepository.findById(id).get();
		return manager;
	}

	@Override
	public String updateHRManager(HRManager hrManager, Integer id) {

		try {

			if (this.hRManagerRepository.existsById(id)) {

				hrManager.setId(id);

				this.hRManagerRepository.save(hrManager);

				return "Id no. : " + id + " is updated ";

			} else {
				return "Id no. : " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return "Id no. :" + id + " is updated ";
	}

	@Override
	public String deleteHRManager(Integer id) {
		try {
			hRManagerRepository.deleteById(id);

			return "Id no. :" + id + " is deleted successfully";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. :" + id + " is not deleted";
	}

	@Override
	public Long nextValue() {
		return this.hRManagerRepository.count();
	}

}
