package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.ApplyLeave_Entity;

public interface ApplyLeave_Service {
	
	ApplyLeave_Entity addLeave(ApplyLeave_Entity leave);
	
	List<ApplyLeave_Entity>getAllLeave();

}
