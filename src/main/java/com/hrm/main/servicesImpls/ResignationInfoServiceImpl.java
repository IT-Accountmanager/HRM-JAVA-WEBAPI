package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.ResignationInfo;
import com.hrm.main.repositories.IResignationInfoRepository;
import com.hrm.main.services.IResignationInfoService;

@Service
public class ResignationInfoServiceImpl implements IResignationInfoService {
	@Autowired
	IResignationInfoRepository resignationInfoRepository;

	@Override
	public String addResignationInfo(ResignationInfo resignInfo) {
		resignationInfoRepository.save(resignInfo);
		return "resignation info added";
	}

}
