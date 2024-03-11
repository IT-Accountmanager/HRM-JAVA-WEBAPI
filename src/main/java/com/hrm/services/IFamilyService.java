package com.hrm.services;

import java.util.List;

import com.hrm.models.Family;
import com.hrm.payloads.FamilyStatusResponse;

public interface IFamilyService {
	String createFamily(Family family, long candidateId);

	List<Family> getAllFamilyByCandidateId(long candidateId);

	Family getFamilyById(int id);

	String updateFamily(Family family, Integer id);

	String deleteFamily(Integer family_id);

	FamilyStatusResponse getFamilyStatusByCandidateId(long candidateId);
}
