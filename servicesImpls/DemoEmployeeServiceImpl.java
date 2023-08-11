package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.DemoEmployee;
import com.hrm.main.repositories.DemoEmployeeRepository;
import com.hrm.main.services.DemoEmployeeService;

@Service
public class DemoEmployeeServiceImpl implements DemoEmployeeService {
	@Autowired
	DemoEmployeeRepository demoEmpRepo;

	@Override
	public DemoEmployee addEmp(DemoEmployee dEmp) {
		DemoEmployee result = this.demoEmpRepo.save(dEmp);
		return result;
	}

	@Override
	public List<DemoEmployee> getEmps() {
		List<DemoEmployee> result = this.demoEmpRepo.findAll();
		return result;
	}

	@Override
	public DemoEmployee updateEmp(DemoEmployee dEmp, int eId) {
		DemoEmployee emp = this.demoEmpRepo.findById(eId).get();
		emp.setName(dEmp.getName());
		emp.setMobNo(dEmp.getMobNo());
		emp.setSalary(dEmp.getSalary());
		
		DemoEmployee result = this.demoEmpRepo.save(emp);
		return result;
	}

	@Override
	public void deleteEmp(Integer eId) {
		this.demoEmpRepo.deleteById(eId);		
	}
	
	

}
