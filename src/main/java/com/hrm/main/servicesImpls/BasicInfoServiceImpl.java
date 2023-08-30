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
	public String updateInfo(BasicInfo basicInfo, Integer id) {

		try {
			if (this.basicInfoRepo.existsById(id)) {
				basicInfo.setId(id);
				this.basicInfoRepo.save(basicInfo);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public BasicInfo getBasicInfo(Integer id) {
		return this.basicInfoRepo.findById(id).get();

	}

}
