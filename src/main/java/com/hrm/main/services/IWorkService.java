package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Work;

public interface IWorkService {

	String createWork(Work work);

	List<Work> getAllWork();
}
