package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Onboarding.CandidatesStatus;


public interface IHRExecutiveService {

	String createExecutive(HRExecutive hrExecutive);

	List<HRExecutive> getAllExecutive();

	HRExecutive getExecutiveById(int id);

	String updateHRExecutive(HRExecutive hrExecutive, Integer id);

	String deleteHrExecutive(Integer id);

	// String tranferProfileToHRExecutive();

	// List<Onboarding> getAllOnboarding(Status ststus);

	boolean postCandidateInHrExecutive(CandidatesStatus status);

	// List<Onboarding> getAllOnboarding();

}
