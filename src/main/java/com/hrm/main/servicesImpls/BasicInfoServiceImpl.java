package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.BasicInfo;
import com.hrm.main.repositories.IBasicInfoRepository;
import com.hrm.main.services.IBasicInfoService;

@Service
public class BasicInfoServiceImpl implements IBasicInfoService {
	@Autowired
	IBasicInfoRepository basicInfoRepo;

	@Override
	public String addBasicInfo(BasicInfo basicInfo) {
		BasicInfo addedBasicInfo = this.basicInfoRepo.save(basicInfo);
		try {
			if (addedBasicInfo.getId() > 0) {
				return "Basic Information Added Successfully of Id : " + addedBasicInfo.getId();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Basic Information Are Not Added ";
	}

	@Override
	public BasicInfo getBasicIndfo(Integer id) {

		return null;
	}

	@Override
	public BasicInfo updateIndfo(BasicInfo basicInfo, Integer id) {

		return null;
	}

}
