package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.HRManager;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;

public interface IHRManagerService {

	boolean postCandidateInHrManager(CandidatesStatus status);

	List<HRManager> getAllHRManager();

	HRManager getHRManager(Integer id);

	String updateHRManager(HRManager hrManager, Integer id);

	String deleteHRManager(Integer id);

	Long nextValue();

}
