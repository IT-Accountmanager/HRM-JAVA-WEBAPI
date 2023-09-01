package com.hrm.main.services;

import com.hrm.main.models.BasicInfo;

public interface IBasicInfoService {

	String addBasicInfo(BasicInfo basicInfo);

	String updateInfo(BasicInfo basicInfo, Integer id);

	BasicInfo getBasicInfo(Integer id);
}
