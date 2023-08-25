package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.BasicInfo;
import com.hrm.main.repositories.IBasicInfoRepository;
import com.hrm.main.services.IBasicInfoService;

@Service

public class BasicInfoServiceImpl implements IBasicInfoService {

	@Autowired
	private IBasicInfoRepository basicInfoRepository;

	@Override
	public String createBasicInfo(BasicInfo basicInfo) {
		try {
			var basicInformation = this.basicInfoRepository.save(basicInfo);
			if (basicInformation.getEmployeeId() > 0)
			{
				return "Basic information is added : " + basicInformation.getEmployeeId();
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return "Baic information is not added " ;
	}

	@Override
	public List<BasicInfo> getAllBasicInfo() {
		List<BasicInfo> allBasic = basicInfoRepository.findAll();
		return allBasic;
	}

}
