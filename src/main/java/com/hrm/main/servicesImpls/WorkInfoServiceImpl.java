package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.WorkInfo;
import com.hrm.main.repositories.IWorkInfoRepository;
import com.hrm.main.services.IWorkInfoService;

@Service
public class WorkInfoServiceImpl implements IWorkInfoService {
	@Autowired
	IWorkInfoRepository workInfoRepo;

	@Override
	public String addWorkInfo(WorkInfo workInfo) {
		WorkInfo addedWorkInfo = this.workInfoRepo.save(workInfo);
		try {
			if (addedWorkInfo.getId() > 0) {
				
				return "Work Information Added Successfully of Id : " + addedWorkInfo.getId();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Work Information Are Not Added ";
	}

	@Override
	public String updateInfo(WorkInfo workInfo, Integer id) {

		try {
			if (this.workInfoRepo.existsById(id)) {
				workInfo.setId(id);
				this.workInfoRepo.save(workInfo);
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
	public WorkInfo getWorkInfo(Integer id) {
		return this.workInfoRepo.findById(id).get();

	}

}
