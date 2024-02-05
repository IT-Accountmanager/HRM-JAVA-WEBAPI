package com.hrm.main.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.config.TwilioConfig;
import com.hrm.main.models.Employee;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import com.hrm.main.models.Helper.EnumCollection.SmsStatus;
import com.hrm.main.models.Helper.SendSMS;
import com.hrm.main.payloads.CandidateStatusDto;
import com.hrm.main.payloads.ExperiencedDto;
import com.hrm.main.payloads.AuthenticateUserDto;
import com.hrm.main.payloads.LinkRequestDto;
import com.hrm.main.payloads.OnboardingDto;
import com.hrm.main.payloads.OnboardingEditDto;
import com.hrm.main.payloads.SMSResponseDto;
import com.hrm.main.payloads.VerifyOtpDto;
import com.hrm.main.payloads.WelcomeDto;
import com.hrm.main.payloads.PasswordDto;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.repositories.IProfileRepository;
import com.hrm.main.services.IOnboardingService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.transaction.Transactional;

@Service
public class OnboardingServiceImpl implements IOnboardingService {

	@Autowired
	IOnboardingRepository onboardingRepository;
	@Autowired
	IProfileRepository profileRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	TwilioConfig twilioConfig;
	@Autowired
	IEmployeeRepository employeeRepository;

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

	@Override
	public SMSResponseDto sendSMS(long candidateId) {
		SMSResponseDto smsResponseDto = null;

		try {
			Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);

			long candidatePhoneNumber = candidate.getContactNumber();
			String link = "http://localhost:4200/welcome";
			String name = candidate.getCandidateName();

			PhoneNumber to = new PhoneNumber("+91" + String.valueOf(candidatePhoneNumber));
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());

			String smsBody = "Dear " + name + "," + "\n\r" + "\n\r"
					+ "Your profile has been selected for Envision Integrated Services. Please click the link below and fill your information. "
					+ "\n\r" + "\n\r" + "Link : " + link;
			Message message = Message.creator(to, from, smsBody).create();

			smsResponseDto = new SMSResponseDto(SmsStatus.DELIVERED, smsBody);
		} catch (Exception e) {
			e.printStackTrace();
			smsResponseDto = new SMSResponseDto(SmsStatus.FAILED, e.getMessage());
		}

		return smsResponseDto;
	}

	@Override
	public SMSResponseDto sendOtp(long candidateId) {
		SMSResponseDto smsResponseDto = null;

		Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);

		try {

			long candidatePhoneNumber = candidate.getContactNumber();

			PhoneNumber to = new PhoneNumber("+91" + String.valueOf(candidatePhoneNumber));
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());
			int randomPin = (int) (Math.random() * 999999) + 100000;
			String otp = String.valueOf(randomPin);
			LocalDateTime requestTime = LocalDateTime.now();

			candidate.setOneTimePassword(otp);
			candidate.setOtpRequestedTime(requestTime);

			this.onboardingRepository.save(candidate);

			String smsBody = "Your ACTUS verification code is : " + otp;

			System.out.println("_____________________________________________");
			System.out.println("OTP : " + otp);
			System.out.println("Request Time : " + requestTime);

			System.out.println("_____________________________________________");

			Message.creator(to, from, smsBody).create();

			smsResponseDto = new SMSResponseDto(SmsStatus.DELIVERED, smsBody);
		} catch (Exception e) {
			e.printStackTrace();
			smsResponseDto = new SMSResponseDto(SmsStatus.FAILED, e.getMessage());
		}

		return smsResponseDto;
	}

	@Override
	public String verifyOtp(VerifyOtpDto verifyOtpDto, long candidateId) {
		Onboarding candidate = this.onboardingRepository.findByCandidateId(candidateId);

		String otp = candidate.getOneTimePassword();
		LocalDateTime otpRequestTime = candidate.getOtpRequestedTime();
		LocalDateTime verifyRequestTime = LocalDateTime.now();
		Duration duration = Duration.between(otpRequestTime, verifyRequestTime);

		if (duration.getSeconds() < 90) {
			if (otp.equals(verifyOtpDto.getOneTimePassword())) {
				return "Verified !";
			} else {
				return "You have entered wrong OTP! Please verify again.";
			}
		} /*
			 * else { return
			 * "Your time limit exceed, Please verify again before 90 second."; }
			 */

		return "Your time limit exceed, Please verify again before 90 second.";
	}

	@Override
	public String addPassword(PasswordDto passwordDto, long candidateId) {
		try {
			Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);
			if (onboarding != null) {
				onboarding.setPassword(passwordDto.getPassword());
				this.onboardingRepository.save(onboarding);
				return "password saved";
			} else {
				return "Candidate not found for candidateId: " + candidateId;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "password not saved - " + e.getMessage();
		}
	}

	/*
	 * // @Transactional
	 * 
	 * @Override public AuthenticateUserDto getPassword(long candidateId) { try {
	 * Employee employee = this.employeeRepository.findByCandidateId(candidateId);
	 * AuthenticateUserDto empDto = new AuthenticateUserDto();
	 * empDto.setEmployeeId(employee.getEmployeeId());
	 * empDto.setPassword(employee.getPassword()); return empDto; } catch (Exception
	 * e) { return null; } }
	 */

	@Override
	public String authenticate(AuthenticateUserDto authenticateUserDto) {
		Onboarding onboarding = this.onboardingRepository.findByEmailIdOrContactNumberAndPassword(
				authenticateUserDto.getEmailId(), authenticateUserDto.getContactNumber(),
				authenticateUserDto.getPassword());
		if (onboarding != null) {
			if (onboarding.getEmailId().equals(authenticateUserDto.getEmailId())
					|| onboarding.getContactNumber() == authenticateUserDto.getContactNumber()
							&& onboarding.getPassword().equals(authenticateUserDto.getPassword())) {
				return "Login successful of Candidate Id : " + "\\r\\n" + onboarding.getCandidateId();
			} else {
				return "Incorrect password";
			}
		} else {
			return "Employee does not exist of Candidate Id : " + authenticateUserDto.getCandidateId();
		}
	}

	@Override
	public WelcomeDto getEmployee(String employeeId) {

		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

		WelcomeDto result = new WelcomeDto();
		result.setName(employee.getName());
		result.setDate(LocalDate.now());

		return result;
	}

	@Override
	public CandidateStatusDto getStatus(long candidateId) {
		Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);
		CandidateStatusDto candidate = new CandidateStatusDto();
		candidate.setCandidatesStatus(onboarding.getCandidatesStatus());

		return candidate;
	}

	@Override
	public boolean setFresherOrExperienced(ExperiencedDto experiencedDto, long candidateId) {
		try {
			Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

			if (onboarding != null) {

				onboarding.setExperience(experiencedDto.getExperience());

				this.onboardingRepository.save(onboarding);

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
