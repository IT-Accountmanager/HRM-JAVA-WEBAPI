package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.DemoEmployee;

public interface DemoEmployeeService {

	DemoEmployee addEmp(DemoEmployee dEmp);

	List<DemoEmployee> getEmps();

	DemoEmployee updateEmp(DemoEmployee dEmp, int eId);

	void deleteEmp(Integer eId);

}
