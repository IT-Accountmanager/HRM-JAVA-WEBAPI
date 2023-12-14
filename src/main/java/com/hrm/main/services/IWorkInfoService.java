package com.hrm.main.services;

import com.hrm.main.payloads.WorkInfoDto;

public interface IWorkInfoService {

	String addWorkInfo(WorkInfoDto workInfo);

	String updateInfo(WorkInfoDto workInfo, Integer id);

	WorkInfoDto getWorkInfo(Integer id);
}
