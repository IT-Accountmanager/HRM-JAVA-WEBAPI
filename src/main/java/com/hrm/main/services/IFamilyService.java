package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Family;

public interface IFamilyService {
	String createFamily(Family family);

	List<Family> getAllFamily();
}
