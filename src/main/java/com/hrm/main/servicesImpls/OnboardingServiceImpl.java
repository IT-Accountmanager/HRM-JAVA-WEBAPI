package com.hrm.main.servicesImpls;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
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

		onboarding.setCandidateId(onboardingRepository.count() + 1);
		onboarding.setCandidateName(onboardingRequest.getCandidateName());
		onboarding.setContactNumber(onboardingRequest.getContactNumber());
		onboarding.setEmailId(onboardingRequest.getEmailId());
		onboarding.setServiceCommitment(onboardingRequest.getServiceCommitment());
		onboarding.setServiceBreakAmount(onboardingRequest.getServiceBreakAmount());
		onboarding.setCtc(onboardingRequest.getCtc());
		onboarding.setCandidatesStatus(CandidatesStatus.Pending);
		onboarding.setDateOfJoining(LocalDate.parse(onboardingRequest.getFormattedDateOfJoining(),
				DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		onboarding.setWorkLocation(onboardingRequest.getWorkLocation());

		// Profile profile = new Profile();
		/*
		 * profile.setOnboarding(onboarding); onboarding.setProfile(profile);
		 */

		return onboardingRepository.save(onboarding);
	}

	@Override
	public List<Onboarding> getAllOnboarding() {
		List<Onboarding> allOnboarding = this.onboardingRepository.findAll();

		// Populate formatted date for each Onboarding object
		for (Onboarding onboarding : allOnboarding) {
			onboarding.setFormattedDate(onboarding.getFormattedDateOfJoining());
		}

		return allOnboarding;
	}

	@Override
	public Onboarding getOnboardingByCandidateId(long candidateId) {
		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

		// Populate formatted date for the retrieved Onboarding object
		if (onboarding != null) {
			onboarding.setFormattedDate(onboarding.getFormattedDateOfJoining());
		}

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

	/*
	 * @Override public String createOnboarding(List<Onboarding> onboardings) {
	 * Onboarding onboarding = new Onboarding();
	 * 
	 * for (Onboarding singleOnboarding : onboardings) {
	 * onboarding.setJobTitle(singleOnboarding.getJobTitle());
	 * 
	 * onboarding.setCandidateId(onboardingRepository.count() + 1);
	 * onboarding.setCandidateName(singleOnboarding.getCandidateName());
	 * onboarding.setContactNumber(singleOnboarding.getContactNumber());
	 * onboarding.setEmailId(singleOnboarding.getEmailId());
	 * onboarding.setServiceCommitment(singleOnboarding.getServiceCommitment());
	 * onboarding.setServiceBreakAmount(singleOnboarding.getServiceBreakAmount());
	 * onboarding.setCtc(singleOnboarding.getCtc());
	 * onboarding.setDepartment(singleOnboarding.getDepartment());
	 * onboarding.setCandidatesStatus(CandidatesStatus.Pending);
	 * onboarding.setDateOfJoining(singleOnboarding.getDateOfJoining());
	 * onboarding.setWorkLocation(singleOnboarding.getWorkLocation()); }
	 * this.onboardingRepository.saveAll(onboarding); return
	 * "Added all Successfully"; }
	 */

	@Override
	public String createOnboarding(List<Onboarding> onboardings) {
		try {
			for (Onboarding singleOnboarding : onboardings) {
				Onboarding onboarding = new Onboarding(); // Create a new instance for each iteration

				// Set values from the input Onboarding
				onboarding.setJobTitle(singleOnboarding.getJobTitle());
				onboarding.setCandidateId(onboardingRepository.count() + 1);
				onboarding.setCandidateName(singleOnboarding.getCandidateName());
				onboarding.setContactNumber(singleOnboarding.getContactNumber());
				onboarding.setEmailId(singleOnboarding.getEmailId());
				onboarding.setServiceCommitment(singleOnboarding.getServiceCommitment());
				onboarding.setServiceBreakAmount(singleOnboarding.getServiceBreakAmount());
				onboarding.setCtc(singleOnboarding.getCtc());
				onboarding.setCandidatesStatus(CandidatesStatus.Pending);
				onboarding.setDateOfJoining(singleOnboarding.getDateOfJoining());
				onboarding.setWorkLocation(singleOnboarding.getWorkLocation());

				onboardingRepository.save(onboarding);
			}
			return "Added all Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error adding onboarding data: " + e.getMessage();
		}
	}

}
