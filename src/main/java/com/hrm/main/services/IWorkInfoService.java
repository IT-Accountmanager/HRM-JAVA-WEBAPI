package com.hrm.main.services;

import com.hrm.main.models.WorkInfo;

public interface IWorkInfoService {

	String addWorkInfo(WorkInfo workInfo);

	String updateInfo(WorkInfo workInfo, Integer id);

	WorkInfo getWorkInfo(Integer id);
}
