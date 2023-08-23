package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Family;
import com.hrm.main.repositories.IFamilyRepository;
import com.hrm.main.services.IFamilyService;

@Service
public class FamilyServiceImpl implements IFamilyService {

	@Autowired
	private IFamilyRepository familyRepo;

	@Override
	public String createFamily(Family family) {
		try {
			var fam = this.familyRepo.save(family);
			if (fam.getId() > 0) {
				return "Family details are added : " + fam.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Family details are not added";
	}

	@Override
	public List<Family> getAllFamily() {
		List<Family> allFam = familyRepo.findAll();
		return allFam;
	}

}
