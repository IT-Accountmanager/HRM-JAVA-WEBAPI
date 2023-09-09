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
			if (fam.getFamilyId() > 0) {
				return "Family details are added : " + fam.getFamilyId();
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

	@Override
	public Family getFamilyById(int id) {

		Family family = familyRepo.findById(id).get();
		return family;
	}

	@Override
	public String updateFamily(Family family, Integer id) {

		try {
			if (this.familyRepo.existsById(id)) {
				family.setFamilyId(id);
				this.familyRepo.save(family);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public String deleteFamily(Integer id) {
		try {
			familyRepo.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

}
