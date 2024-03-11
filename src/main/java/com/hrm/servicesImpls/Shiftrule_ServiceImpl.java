package com.hrm.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.ShiftRule_Entity;
import com.hrm.repositories.ShiftRule_Repo;
import com.hrm.services.ShiftRule_Service;

@Service

public class Shiftrule_ServiceImpl implements ShiftRule_Service{
	@Autowired
	ShiftRule_Repo shiftRule_Repo;
	
	@Override
	public ShiftRule_Entity addShiftRule(ShiftRule_Entity shiftRule) {
		return shiftRule_Repo.save(shiftRule);
	}
	
	@Override
	public List<ShiftRule_Entity>getAllShiftRule(){
		return shiftRule_Repo.findAll();
	}

}
