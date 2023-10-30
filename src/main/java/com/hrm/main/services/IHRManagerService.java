package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;

public interface IHRManagerService {

	boolean postCandidateInHrManager(CandidatesStatus status);

	List<Onboarding> getAllHRManager(CandidatesStatus status);

	HRManager getHRManager(Integer id);

	String updateHRManager(HRManager hrManager, Integer id);

	String deleteHRManager(Integer id);

	Long nextValue();

}
