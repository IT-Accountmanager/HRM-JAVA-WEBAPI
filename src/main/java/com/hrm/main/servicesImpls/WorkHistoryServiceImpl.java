package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.WorkHistory;
import com.hrm.main.repositories.IWorkHistoryRepository;
import com.hrm.main.services.IWorkHistoryService;

@Service
public class WorkHistoryServiceImpl implements IWorkHistoryService {

	@Autowired
	IWorkHistoryRepository workHistoryRepo;

	@Override
	public String addWorkHistory(WorkHistory workHistory) {
		WorkHistory addedWorkHistory = this.workHistoryRepo.save(workHistory);
		try {
			if (addedWorkHistory.getDeptId() > 0) {
				return "Work History Added Successfully of Id : " + addedWorkHistory.getDeptId();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Work History Are Not Added ";
	}

	@Override
	public String updateWorkHistory(WorkHistory workHistory, Integer id) {

		try {
			if (this.workHistoryRepo.existsById(id)) {
				workHistory.setDeptId(id);
				this.workHistoryRepo.save(workHistory);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public WorkHistory getWorkHistory(Integer id) {
		return this.workHistoryRepo.findById(id).get();

	}

}
