package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.BasicInfo;

public interface IBasicInfoService {
	
	String createBasicInfo(BasicInfo basicInfo);
	
	List<BasicInfo> getAllBasicInfo();

}
