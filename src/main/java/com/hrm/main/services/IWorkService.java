package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Work;

public interface IWorkService {

	String createWork(Work work, int candidateId);

	List<Work> getAllWork();

	Work getWork(Integer id);

	String updateWork(Work work, Integer id);

	String deleteWork(Integer id);

	String getDocument(Integer id);

}
