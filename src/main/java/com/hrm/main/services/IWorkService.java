package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Work;
import com.hrm.main.payloads.WorkStatusResponse;

public interface IWorkService {

	String createWork(Work work, long candidateId);

	List<Work> getAllWorkByCandidateId(long candidateId);

	Work getWork(Integer id);

	String updateWork(Work work, Integer id);

	String deleteWork(Integer id);

	String getDocument(Integer id);

	WorkStatusResponse getWorkStatusByCandidateId(long candidateId);

}
