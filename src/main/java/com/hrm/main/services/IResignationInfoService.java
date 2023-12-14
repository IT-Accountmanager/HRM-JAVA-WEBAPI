package com.hrm.main.services;

import com.hrm.main.payloads.ResignationInfoDto;

public interface IResignationInfoService {

	String addResignationInfo(ResignationInfoDto resignInfo);

	String updateResignationInfo(ResignationInfoDto resignInfo, Integer id);

	ResignationInfoDto getResignationInfo(Integer id);
}
