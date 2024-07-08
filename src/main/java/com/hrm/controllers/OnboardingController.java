package com.hrm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.models.Onboarding;
import com.hrm.payloads.AuthenticateUserDto;
import com.hrm.payloads.CandidateStatusDto;
import com.hrm.payloads.ExperiencedDto;
import com.hrm.payloads.LoginWelcomeDto;
import com.hrm.payloads.OnboardingDto;
import com.hrm.payloads.OnboardingEditDto;
import com.hrm.payloads.PasswordDto;
import com.hrm.payloads.SMSResponseDto;
import com.hrm.payloads.UserLoginResponseDto;
import com.hrm.payloads.VerifyOtpDto;
import com.hrm.payloads.WelcomeDto;
import com.hrm.services.IOnboardingService;

import io.swagger.v3.oas.annotations.Operation;

//@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Onboarding")

public class OnboardingController {

	@Autowired
	IOnboardingService onboardingService;

	public static final Logger logger = LoggerFactory.getLogger(OnboardingController.class);

	/*
	 * @PostMapping("/post") public ResponseEntity<String>
	 * addOnboarding(@RequestBody Onboarding onboarding) { String createOnboarding =
	 * this.onboardingService.createOnboarding(onboarding); return new
	 * ResponseEntity<String>(createOnboarding, HttpStatus.CREATED); }
	 */

	@PostMapping("/post")
	public ResponseEntity<Onboarding> addOnboarding(@RequestBody Onboarding onboardingRequest) {

		Onboarding createOnboarding = this.onboardingService.createOnboarding(onboardingRequest);
		return new ResponseEntity<Onboarding>(createOnboarding, HttpStatus.OK);
	}

	@PostMapping("/post/import")
	public ResponseEntity<Long> addOnboarding(@RequestBody List<Onboarding> onboardings) {
		Long result = this.onboardingService.createOnboarding(onboardings);
		return new ResponseEntity<Long>(result, HttpStatus.CREATED);
	}

	@Operation(summary = "Get All Onboarded Candidates")
	@GetMapping("/get")
	public ResponseEntity<List<OnboardingDto>> getAllOnboarding() {
		List<OnboardingDto> allOnboarding = this.onboardingService.getAllOnboarding();
		return new ResponseEntity<List<OnboardingDto>>(allOnboarding, HttpStatus.OK);
	}

	@Operation(summary = "Get Candidate By candidate Id")
	@GetMapping("/get/{candidateId}")
	public ResponseEntity<Onboarding> getOnboarding(@PathVariable long candidateId) {
		Onboarding onboarding = this.onboardingService.getOnboardingByCandidateId(candidateId);
		return new ResponseEntity<Onboarding>(onboarding, HttpStatus.OK);
	}

	@Operation(summary = "Update candidate By Candidate Id ")
	@PostMapping("/update/{candidateId}")
	public ResponseEntity<String> getOnboarding(@RequestBody OnboardingEditDto onboardingDto,
			@PathVariable long candidateId) {
		String updateOnboarding = this.onboardingService.updateOnboarding(onboardingDto, candidateId);
		return new ResponseEntity<String>(updateOnboarding, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOnboarding(@PathVariable Integer id) {
		String deleteOnboarding = this.onboardingService.deleteOnboarding(id);
		return new ResponseEntity<String>(deleteOnboarding, HttpStatus.OK);
	}

	@GetMapping("/sms/{candidateId}")
	public String sendSmsToCandidate(@PathVariable long candidateId) {
		String result = this.onboardingService.sendSmstoCandidate(candidateId);
		return result;
	}

	@PostMapping("send-link/{candidateId}")
	public SMSResponseDto sendLink(@PathVariable long candidateId) {
		return this.onboardingService.sendSMS(candidateId);
	}

	@PostMapping("/send-link-e/{candidateId}")
	public ResponseEntity<String> sendMail(@PathVariable long candidateId) {
		String result = this.onboardingService.sendSimpleMail(candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping("send-otp-m/{candidateId}")
	public SMSResponseDto sendOtp(@PathVariable long candidateId) {
		return this.onboardingService.sendMobileOtp(candidateId);
	}

	@PostMapping("send-otp-e/{candidateId}")
	public ResponseEntity<String> sendOtpToMail(@PathVariable long candidateId) {
		String result = this.onboardingService.sendEmailOtp(candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("verify-otp/{candidateId}")
	public String verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto, @PathVariable long candidateId) {
		return this.onboardingService.verifyOtp(verifyOtpDto, candidateId);
	}

	@PostMapping("add-password/{candidateId}")
	public ResponseEntity<String> addPassword(@RequestBody PasswordDto passwordDto, @PathVariable long candidateId) {
		String result = this.onboardingService.addPassword(passwordDto, candidateId);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticateUserDto authenticateUserDto) {
		try {
			if (authenticateUserDto == null) {
				logger.error("Invalid Input : AuthenticateUserDto is null");
				return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
			}

			UserLoginResponseDto result = onboardingService.authenticate(authenticateUserDto);

			if (result != null) {
				logger.info("Candidate Authenticate Successfully : {}", result.getCandidateId());
				return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
			} else {
				logger.warn("Authentication failed for : {}", result);
				logger.warn("Authentication failed for : {}", authenticateUserDto.getUsername());
				return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			logger.error("An error occurred during authentication: {}", e.getMessage(), e);

		}
		return null;

	}

	@GetMapping("employee/{employeeId}")
	public ResponseEntity<WelcomeDto> getEmployee(@PathVariable String employeeId) {
		WelcomeDto result = this.onboardingService.getEmployee(employeeId);
		return new ResponseEntity<WelcomeDto>(result, HttpStatus.OK);
	}

	@GetMapping("/departments")
	public String[] getDepartments() {
		Departments[] departments = Departments.values();
		return formatDepartments(departments);
	}

	/*
	 * @GetMapping("/subDepartments/{department}") public Departments[]
	 * getSubDepartments(@PathVariable("department") Departments department) {
	 * return department.getSubdepartments(); }
	 */

	@GetMapping("/subDepartments/{department}")
	public Departments.Department[] getSubDepartments(@PathVariable("department") Departments department) {
		return department.getSubdepartments();
	}

	private String[] formatDepartments(Departments[] departments) {
		String[] formattedDepartments = new String[departments.length];

		for (int i = 0; i < departments.length; i++) {
			formattedDepartments[i] = formatDepartmentName(departments[i].name());
		}

		return formattedDepartments;
	}

	private String formatDepartmentName(String departmentName) {
		// Replace camel case with spaces and capitalize each word
		return departmentName.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

	@GetMapping("/status/{candidateId}")
	public ResponseEntity<CandidateStatusDto> getStatus(@PathVariable long candidateId) {
		CandidateStatusDto result = this.onboardingService.getStatus(candidateId);
		return new ResponseEntity<CandidateStatusDto>(result, HttpStatus.OK);
	}

	@PostMapping("/experience-status/{candidateId}")
	public ResponseEntity<String> updateFresherStatus(@RequestBody ExperiencedDto experiencedDto,
			@PathVariable long candidateId) {

		boolean updated = this.onboardingService.setFresherOrExperienced(experiencedDto, candidateId);

		if (updated) {
			return ResponseEntity.ok("Experience status updated successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidate with ID " + candidateId + " not found.");
		}
	}

	@GetMapping("/detail/{candidateId}")
	public ResponseEntity<LoginWelcomeDto> getDetails(@PathVariable long candidateId) {
		LoginWelcomeDto details = this.onboardingService.getDetails(candidateId);
		return new ResponseEntity<LoginWelcomeDto>(details, HttpStatus.OK);
	}

}
