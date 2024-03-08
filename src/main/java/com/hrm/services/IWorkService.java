package com.hrm.services;

import java.util.List;

import com.hrm.models.Work;
import com.hrm.payloads.WorkStatusResponse;

public interface IWorkService {

	String createWorkForExperiencedCandidate(Work work, long candidateId);

	String createWorkForFresherCandidate(long candidateId);

	List<Work> getAllWorkByCandidateId(long candidateId);

	Work getWork(Integer id);

	String updateWork(Work work, Integer id);

	String deleteWork(Integer id);

	String getDocument(Integer id);

	WorkStatusResponse getWorkStatusByCandidateId(long candidateId);
	

	// String setExperienced(long candidateId);

}
