package com.hrm.main.services;

import com.hrm.main.models.BasicInfo;

public interface IBasicInfoService {

	String addBasicInfo(BasicInfo basicInfo);

	BasicInfo getBasicIndfo(Integer id);

	BasicInfo updateIndfo(BasicInfo basicInfo, Integer id);
}
