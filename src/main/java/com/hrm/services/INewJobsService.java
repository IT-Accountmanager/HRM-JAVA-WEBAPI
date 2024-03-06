package com.hrm.services;

import java.util.List;

import com.hrm.models.NewJobs;

public interface INewJobsService {
	
	String addNewJobs(NewJobs newJobs);

	List<NewJobs> allJobs();

	NewJobs getJob(Integer id);

	String updateJob(NewJobs newJobs, Integer id);

	String deleteJob(Integer id);

}
