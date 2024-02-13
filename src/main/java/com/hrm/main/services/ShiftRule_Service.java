package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.ShiftRule_Entity;

public interface ShiftRule_Service {
	ShiftRule_Entity addShiftRule(ShiftRule_Entity shiftRule);
	
	List<ShiftRule_Entity>getAllShiftRule();

}
