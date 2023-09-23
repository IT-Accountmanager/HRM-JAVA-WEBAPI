package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Family;

public interface IFamilyService {
	String createFamily(Family family, int candidateId);

	List<Family> getAllFamily();

	Family getFamilyById(int id);

	String updateFamily(Family family, Integer id);

	String deleteFamily(Integer family_id);
}
