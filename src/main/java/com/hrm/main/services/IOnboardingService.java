package com.hrm.main.services;

import java.util.List;
import com.hrm.main.models.Onboarding;
import com.hrm.main.payloads.CandidateStatusDto;
import com.hrm.main.payloads.AuthenticateUserDto;
import com.hrm.main.payloads.LinkRequestDto;
import com.hrm.main.payloads.OnboardingDto;
import com.hrm.main.payloads.OnboardingEditDto;
import com.hrm.main.payloads.SMSResponseDto;
import com.hrm.main.payloads.VerifyOtpDto;
import com.hrm.main.payloads.WelcomeDto;
import com.hrm.main.payloads.PasswordDto;

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

	SMSResponseDto sendOtp(long candidateId);

	String verifyOtp(VerifyOtpDto verifyOtpDto, long candidateId);

	String addPassword(PasswordDto passwordDto, long candidateId);

	// AuthenticateUserDto getPassword(long candidateId);

	String authenticate(AuthenticateUserDto employeeIdPasswordDto);

	WelcomeDto getEmployee(String employeeId);

	CandidateStatusDto getStatus(long candidateId);


}
