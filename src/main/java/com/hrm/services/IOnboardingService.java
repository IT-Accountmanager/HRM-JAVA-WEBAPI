package com.hrm.services;

import java.util.List;

import com.hrm.models.Email;
import com.hrm.models.Onboarding;
import com.hrm.payloads.AuthenticateUserDto;
import com.hrm.payloads.CandidateStatusDto;
import com.hrm.payloads.ExperiencedDto;
import com.hrm.payloads.LoginWelcomeDto;
import com.hrm.payloads.OnboardingDto;
import com.hrm.payloads.OnboardingEditDto;
import com.hrm.payloads.PasswordDto;
import com.hrm.payloads.SMSResponseDto;
import com.hrm.payloads.VerifyOtpDto;
import com.hrm.payloads.WelcomeDto;

public interface IOnboardingService {

	Onboarding createOnboarding(Onboarding onboardingRequest);

	Long createOnboarding(List<Onboarding> onboardings);

	List<OnboardingDto> getAllOnboarding();

	Onboarding getOnboardingByCandidateId(long candidateId);

	String updateOnboarding(OnboardingEditDto onboardingDto, long candidateId);

	String deleteOnboarding(Integer id);

	Long nextValue();

	String sendSmstoCandidate(long candidateId);

	SMSResponseDto sendSMS(long canidateId);

	SMSResponseDto sendMobileOtp(long candidateId);

	String verifyOtp(VerifyOtpDto verifyOtpDto, long candidateId);

	String addPassword(PasswordDto passwordDto, long candidateId);

	// AuthenticateUserDto getPassword(long candidateId);

	String authenticate(AuthenticateUserDto employeeIdPasswordDto);

	WelcomeDto getEmployee(String employeeId);

	CandidateStatusDto getStatus(long candidateId);

	boolean setFresherOrExperienced(ExperiencedDto fresherOrExperiencedDto, long candidateId);

	String sendSimpleMail(long candidateId);

	String sendEmailOtp(long candidateId);

	LoginWelcomeDto getDetails(long candidateId);

}
