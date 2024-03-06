package com.hrm.services;

import com.hrm.payloads.BasicInfoDto;

public interface IBasicInfoService {

	String addBasicInfo(BasicInfoDto basicInfo);

	String updateInfo(BasicInfoDto basicInfo, Integer id);

	BasicInfoDto getBasicInfo(Integer id);
}
