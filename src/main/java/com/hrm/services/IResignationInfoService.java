package com.hrm.services;

import com.hrm.payloads.ResignationInfoDto;

public interface IResignationInfoService {

	String addResignationInfo(ResignationInfoDto resignInfo);

	String updateResignationInfo(ResignationInfoDto resignInfo, Integer id);

	ResignationInfoDto getResignationInfo(Integer id);
}
