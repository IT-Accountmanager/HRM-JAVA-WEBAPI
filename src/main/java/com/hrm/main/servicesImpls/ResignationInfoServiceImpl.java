package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.ResignationInfo;
import com.hrm.main.models.WorkHistory;
import com.hrm.main.repositories.IResignationInfoRepository;
import com.hrm.main.services.IResignationInfoService;

@Service
public class ResignationInfoServiceImpl implements IResignationInfoService {
	@Autowired
	IResignationInfoRepository resignationInfoRepository;

	@Override
	public String addResignationInfo(ResignationInfo resignInfo) {
		ResignationInfo save = this.resignationInfoRepository.save(resignInfo);
		try {
			if (save.getId() > 0) {
				return "Resignation Info Added Successfully of Id : " + save.getId();
			}

		} catch (Exception ex) {
			ex.getMessage();
		}
		return "Resignation Info Are Not Added ";
	}

	@Override
	public String updateResignationInfo(ResignationInfo resignInfo, Integer id) {

		try {
			if (this.resignationInfoRepository.existsById(id)) {
				resignInfo.setId(id);
				this.resignationInfoRepository.save(resignInfo);
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
	public ResignationInfo getResignationInfo(Integer id) {
		return this.resignationInfoRepository.findById(id).get();

	}

}
