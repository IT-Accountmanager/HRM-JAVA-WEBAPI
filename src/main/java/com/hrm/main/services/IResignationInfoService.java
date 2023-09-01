package com.hrm.main.services;

import com.hrm.main.models.ResignationInfo;

public interface IResignationInfoService {

	String addResignationInfo(ResignationInfo resignInfo);

	String updateResignationInfo(ResignationInfo resignInfo, Integer id);

	ResignationInfo getResignationInfo(Integer id);
}
