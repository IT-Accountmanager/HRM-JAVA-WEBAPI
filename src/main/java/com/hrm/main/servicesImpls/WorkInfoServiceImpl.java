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

}
