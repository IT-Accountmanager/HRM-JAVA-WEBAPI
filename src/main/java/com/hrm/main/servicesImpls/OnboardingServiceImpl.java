package com.hrm.main.servicesImpls;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import com.hrm.main.models.Helper.SendSMS;
import com.hrm.main.payloads.OnboardingDto;
import com.hrm.main.payloads.OnboardingEditDto;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IProfileRepository;
import com.hrm.main.services.IOnboardingService;

@Service
public class OnboardingServiceImpl implements IOnboardingService {

	@Autowired
	IOnboardingRepository onboardingRepository;

	@Autowired
	IProfileRepository profileRepository;
	@Autowired
	ModelMapper modelMapper;

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
		onboarding.setJobTitleDesignation(onboardingRequest.getJobTitleDesignation());
		onboarding.setCandidateId(onboardingRepository.count() + 1);
		onboarding.setCandidateName(onboardingRequest.getCandidateName());
		onboarding.setContactNumber(onboardingRequest.getContactNumber());
		onboarding.setEmailId(onboardingRequest.getEmailId());
		onboarding.setServiceCommitment(onboardingRequest.getServiceCommitment());
		onboarding.setServiceBreakAmount(onboardingRequest.getServiceBreakAmount());
		onboarding.setCtc(onboardingRequest.getCtc());
		onboarding.setCandidatesStatus(CandidatesStatus.Pending);
		onboarding.setHrExecutiveSubmission(HrSubmission.Pending);
		onboarding.setHrManagerSubmission(HrSubmission.Pending);
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
	public List<OnboardingDto> getAllOnboarding() {
		List<Onboarding> allOnboarding = this.onboardingRepository.findAll();

		List<OnboardingDto> onboardingDtos = new ArrayList<OnboardingDto>();

		for (Onboarding onboarding : allOnboarding) {
			OnboardingDto map = this.modelMapper.map(onboarding, OnboardingDto.class);
			onboardingDtos.add(map);
		}

		return onboardingDtos;
	}

	@Override
	public Onboarding getOnboardingByCandidateId(long candidateId) {
		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

		if (onboarding != null) {
			onboarding.setFormattedDate(onboarding.getFormattedDateOfJoining());
		}

		return onboarding;
	}

	@Override
	public String updateOnboarding(OnboardingEditDto onboardingDto, long candidateId) {
		Onboarding existingCandidate = this.onboardingRepository.findByCandidateId(candidateId);

		if (existingCandidate != null) {
			modelMapper.map(onboardingDto, existingCandidate);

			this.onboardingRepository.save(existingCandidate);

			return "Candidate is updated : " + candidateId;
		} else {
			return "Candidate not found with id: " + candidateId;
		}
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
	public Long createOnboarding(List<Onboarding> onboardings) {
		try {
			for (Onboarding singleOnboarding : onboardings) {
				Onboarding onboarding = new Onboarding(); // Create a new instance for each iteration

				// Set values from the input Onboarding
				onboarding.setJobTitleDesignation(singleOnboarding.getJobTitleDesignation());
				onboarding.setCandidateId(onboardingRepository.count() + 1);
				onboarding.setCandidateName(singleOnboarding.getCandidateName());
				onboarding.setContactNumber(singleOnboarding.getContactNumber());
				onboarding.setEmailId(singleOnboarding.getEmailId());
				onboarding.setServiceCommitment(singleOnboarding.getServiceCommitment());
				onboarding.setServiceBreakAmount(singleOnboarding.getServiceBreakAmount());
				onboarding.setCtc(singleOnboarding.getCtc());
				onboarding.setCandidatesStatus(CandidatesStatus.Pending);
				onboarding.setHrExecutiveSubmission(HrSubmission.Pending);
				onboarding.setHrManagerSubmission(HrSubmission.Pending);
				onboarding.setDateOfJoining(singleOnboarding.getDateOfJoining());
				onboarding.setWorkLocation(singleOnboarding.getWorkLocation());

				Onboarding savedOnboarding = onboardingRepository.save(onboarding);

				singleOnboarding.setCandidateId(savedOnboarding.getCandidateId());
			}

			return onboardings.isEmpty() ? null : onboardings.get(0).getCandidateId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * @Override public String sendSmstoCandidate(long candidateId) { Onboarding
	 * onboarding = this.onboardingRepository.findByCandidateId(candidateId); long
	 * contactNumber = onboarding.getContactNumber(); SendSMS.sendSMS("Hello",
	 * contactNumber); return "message sent"; }
	 */

	@Override
	public String sendSmstoCandidate(long candidateId) {
		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

		if (onboarding == null) {
			// Handle the case where no Onboarding record is found for the given candidateId
			// You might want to log this event or return a different message
			return "No Onboarding record found for candidateId: " + candidateId;
		}

		long contactNumber = onboarding.getContactNumber();

		try {
			SendSMS.sendSMS("Hello");
			// Log success if needed
			return "Message sent successfully";
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception details
			return "Failed to send message";
		}
	}
}
