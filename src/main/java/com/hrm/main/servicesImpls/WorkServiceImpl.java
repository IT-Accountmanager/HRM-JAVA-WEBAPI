package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Work;
import com.hrm.main.repositories.IWorkRepository;
import com.hrm.main.services.IWorkService;
@Service
public class WorkServiceImpl implements IWorkService {

	@Autowired
	private IWorkRepository workRepo;

	@Override
	public String createWork(Work work) {
		try {
			var work1 = this.workRepo.save(work);
			if (work1.getId() > 0) {
				return "Work details are added : " + work1.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "work details are not added : ";

	}

	@Override
	public List<Work> getAllWork() {
		List<Work> allWork = workRepo.findAll();
		return allWork;
	}

}
