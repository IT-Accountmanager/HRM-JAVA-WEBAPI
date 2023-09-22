package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Personal;
import com.hrm.main.repositories.IPersonalRepository;
import com.hrm.main.services.IPersonalService;

@Service
public class PersonalServiceImpl implements IPersonalService {

	@Autowired
	IPersonalRepository personalRepository;

	@Override
	public String addPersonal(Personal personal, Integer candidateId) {
		try {
			personal.setCandidateId(candidateId);
			var fam = this.personalRepository.save(personal);
			if (fam.getPersonalId() > 0) {
				return "Personal details are added : " + fam.getPersonalId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Personal details are not added";
	}
}
