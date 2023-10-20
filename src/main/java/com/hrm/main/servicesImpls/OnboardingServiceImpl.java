package com.hrm.main.servicesImpls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Onboarding.CandidatesStatus;
import com.hrm.main.models.Profile;
import com.hrm.main.models.Regularization;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IProfileRepository;
import com.hrm.main.services.IOnboardingService;

@Service
public class OnboardingServiceImpl implements IOnboardingService {

	@Autowired
	IOnboardingRepository onboardingRepository;

	@Autowired
	IProfileRepository profileRepository;

	/*
	 * @Override public String createOnboarding(Onboarding onboarding) { try {
	 * //onboarding.getProfile().setProfileId(); Onboarding save =
	 * this.onboardingRepository.save(onboarding);
	 * 
	 * 
	 * if (save.getSrNo() > 0) { return "Onboarding of Id no. " + save.getSrNo() +
	 * " is Successfully Added!"; } return "Onboarding is not Added!";
	 * 
	 * } catch (Exception e) { e.getMessage(); } return "Onboarding is not added!";
	 * 
	 * }
	 */

	@Override
	public Onboarding createOnboarding(Onboarding onboardingRequest) {
		Onboarding onboarding = new Onboarding();
		onboarding.setJobTitle(onboardingRequest.getJobTitle());

		onboarding.setCandidateId("EIS" + String.format("%06d", onboardingRepository.count() + 1));
		onboarding.setCandidateName(onboardingRequest.getCandidateName());
		onboarding.setContactNumber(onboardingRequest.getContactNumber());
		onboarding.setEmailId(onboardingRequest.getEmailId());
		onboarding.setBondPeriod(onboardingRequest.getBondPeriod());
		onboarding.setBondBreakAmount(onboardingRequest.getBondBreakAmount());
		onboarding.setCtc(onboardingRequest.getCtc());

		onboarding.setCandidatesStatus(CandidatesStatus.Pending);

		// Profile profile = new Profile();
		/*
		 * profile.setOnboarding(onboarding); onboarding.setProfile(profile);
		 */

		return onboardingRepository.save(onboarding);
	}

	@Override
	public List<Onboarding> getAllOnboarding() {
		List<Onboarding> allOnboarding = this.onboardingRepository.findAll();
		return allOnboarding;
	}

	@Override
	public Onboarding getOnboardingById(int id) {
		Onboarding onboarding = this.onboardingRepository.findById(id).get();
		return onboarding;
	}

	@Override
	public String updateOnboarding(Onboarding onboarding, Integer id) {

		try {
			if (this.onboardingRepository.existsById(id)) {
				onboarding.setSrNo(id);
				this.onboardingRepository.save(onboarding);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public String deleteOnboarding(Integer id) {

		try {
			this.onboardingRepository.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";

	}

	@Override
	public Long nextValue() {
		return this.onboardingRepository.count();
	}

	@Override
	public String createOnboarding(List<Onboarding> onboardings) {
		this.onboardingRepository.saveAll(onboardings);
		return "Added all Successfully";
	}

}
