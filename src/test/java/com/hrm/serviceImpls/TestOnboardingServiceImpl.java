package com.hrm.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.HrSubmission;
import com.hrm.helper.EnumCollection.WorkLocation;
import com.hrm.models.Onboarding;
import com.hrm.repositories.IOnboardingRepository;
import com.hrm.servicesImpls.OnboardingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestOnboardingServiceImpl {

	@Mock
	private IOnboardingRepository onboardingRepository;

	@InjectMocks
	private OnboardingServiceImpl onboardingServiceImpl;

//	@Test
//	public void testCreateOnboarding() {
//		// Arrange
//		Onboarding onboardingRequest = new Onboarding();
//		onboardingRequest.setJobTitleDesignation(Designation.TEAM_LEAD);
//		onboardingRequest.setCandidateName("John Doe");
//		onboardingRequest.setContactNumber(1234567890L);
//		onboardingRequest.setEmailId("john.doe@example.com");
//		onboardingRequest.setServiceCommitment(1.0f);
//		onboardingRequest.setServiceBreakAmount(50000L);
//		onboardingRequest.setCtc(60000L);
//		onboardingRequest.setDateOfJoining(LocalDate.parse("01-01-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//		onboardingRequest.setWorkLocation(WorkLocation.HYDERABAD);
//		onboardingRequest.setCandidateId(3L); // Ensure candidateId is set
//		onboardingRequest.setCandidatesStatus(CandidatesStatus.Pending);
//		onboardingRequest.setHrExecutiveSubmission(HrSubmission.Pending);
//		onboardingRequest.setHrManagerSubmission(HrSubmission.Pending);
//
//		Onboarding existingOnboarding1 = new Onboarding();
//		existingOnboarding1.setCandidateId(1L);
//		Onboarding existingOnboarding2 = new Onboarding();
//		existingOnboarding2.setCandidateId(2L);
//
//		List<Onboarding> findAll = Arrays.asList(existingOnboarding1, existingOnboarding2);
//
//		when(onboardingRepository.findAll()).thenReturn(findAll);
//		when(onboardingRepository.save(any(Onboarding.class))).thenReturn(onboardingRequest);
//
//		// Act
//		Onboarding savedOnboarding = onboardingServiceImpl.createOnboarding(onboardingRequest);
//
//		// Assert
//		assertEquals(3L, savedOnboarding.getCandidateId());
//		assertEquals(Designation.TEAM_LEAD, savedOnboarding.getJobTitleDesignation());
//		assertEquals("John Doe", savedOnboarding.getCandidateName());
//		assertEquals(1234567890L, savedOnboarding.getContactNumber());
//		assertEquals("john.doe@example.com", savedOnboarding.getEmailId());
//		assertEquals(1.0f, savedOnboarding.getServiceCommitment());
//		assertEquals(50000L, savedOnboarding.getServiceBreakAmount());
//		assertEquals(60000L, savedOnboarding.getCtc());
//		assertEquals(CandidatesStatus.Pending, savedOnboarding.getCandidatesStatus());
//		assertEquals(HrSubmission.Pending, savedOnboarding.getHrExecutiveSubmission());
//		assertEquals(HrSubmission.Pending, savedOnboarding.getHrManagerSubmission());
//		assertEquals(LocalDate.parse("01-01-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
//				savedOnboarding.getDateOfJoining());
//
//		assertEquals(WorkLocation.HYDERABAD, savedOnboarding.getWorkLocation());
//	}

	@Test
	public void createObTest() {
		// Arrange
		Onboarding onboardingRequest = new Onboarding();
		onboardingRequest.setJobTitleDesignation(Designation.PROGRAM_MANAGER);
		// onboardingRequest.setCandidateId(3L);
		onboardingRequest.setCandidateName("Ram");
		onboardingRequest.setContactNumber(9087654321L);
		onboardingRequest.setEmailId("Ram@gmail.com");
		onboardingRequest.setServiceCommitment(1.5f);
		onboardingRequest.setServiceBreakAmount(100000L);
		onboardingRequest.setCtc(1250000L);
		onboardingRequest.setCandidatesStatus(CandidatesStatus.Pending);
		onboardingRequest.setHrExecutiveSubmission(HrSubmission.Pending);
		onboardingRequest.setHrManagerSubmission(HrSubmission.Pending);
		onboardingRequest.setDateOfJoining(LocalDate.parse("03-08-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		onboardingRequest.setWorkLocation(WorkLocation.CHENNAI);

		Onboarding existingOnboarding1 = new Onboarding();
		existingOnboarding1.setCandidateId(1L);

		Onboarding existingOnboarding2 = new Onboarding();
		existingOnboarding2.setCandidateId(2L);

		List<Onboarding> all = Arrays.asList(existingOnboarding1, existingOnboarding2);

		when(onboardingRepository.findAll()).thenReturn(all);
		when(onboardingRepository.save(any(Onboarding.class))).thenReturn(onboardingRequest);

		long maxCandidateId = 0;
		for (Onboarding onboarding : all) {
			long candidateId = onboarding.getCandidateId();
			if (candidateId > maxCandidateId) {
				maxCandidateId = candidateId;
			}
		}
		maxCandidateId++;
		onboardingRequest.setCandidateId(maxCandidateId);

		// Act
		Onboarding savedOnboarding = onboardingServiceImpl.createOnboarding(onboardingRequest);

		// Assert
		String failMsg = " Assert fail.";
		assertEquals(3L, savedOnboarding.getCandidateId(), "Candidate Id" + failMsg);
		assertEquals(Designation.PROGRAM_MANAGER, savedOnboarding.getJobTitleDesignation(), "Job Title" + failMsg);
		assertEquals("Ram", savedOnboarding.getCandidateName(), "Candidate Name" + failMsg);
		assertEquals(9087654321L, savedOnboarding.getContactNumber(), "Contact No" + failMsg);
		assertEquals("Ram@gmail.com", savedOnboarding.getEmailId(), "Email Id" + failMsg);
		assertEquals(1.5f, savedOnboarding.getServiceCommitment(), "Service Commitement" + failMsg);
		assertEquals(100000L, savedOnboarding.getServiceBreakAmount(), "Service Break Amount" + failMsg);
	}
}