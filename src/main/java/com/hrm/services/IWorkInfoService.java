package com.hrm.services;

import com.hrm.payloads.WorkInfoDto;

public interface IWorkInfoService {

	String addWorkInfo(WorkInfoDto workInfo);

	String updateInfo(WorkInfoDto workInfo, Integer id);

	WorkInfoDto getWorkInfo(Integer id);
}
