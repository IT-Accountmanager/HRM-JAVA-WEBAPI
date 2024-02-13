package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.ApplyLeave_Entity;
import com.hrm.main.repositories.ApplyLeave_Repo;
import com.hrm.main.services.ApplyLeave_Service;

@Service

public class ApplyLeave_ServiceImpl implements ApplyLeave_Service{
	@Autowired
	ApplyLeave_Repo applyLeave_Repo;
	
	@Override
	public ApplyLeave_Entity addLeave(ApplyLeave_Entity leave) {
		return applyLeave_Repo.save(leave);
	}
	
	@Override
	public List<ApplyLeave_Entity>getAllLeave(){
		return applyLeave_Repo.findAll();
	}
	

}
