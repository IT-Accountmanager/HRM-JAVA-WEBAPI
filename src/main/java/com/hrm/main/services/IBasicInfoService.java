package com.hrm.main.services;

import com.hrm.main.payloads.BasicInfoDto;

public interface IBasicInfoService {

	String addBasicInfo(BasicInfoDto basicInfo);

	String updateInfo(BasicInfoDto basicInfo, Integer id);

	BasicInfoDto getBasicInfo(Integer id);
}
