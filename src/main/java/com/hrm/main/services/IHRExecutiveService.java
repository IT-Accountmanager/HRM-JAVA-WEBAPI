package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Personal;

public interface IHRExecutiveService {

	String createExecutive(HRExecutive hrExecutive);

	List<HRExecutive> getAllExecutive();

	HRExecutive getExecutiveById(int id);

	String updateHRExecutive(HRExecutive hrExecutive, Integer id);

	String deleteHrExecutive(Integer id);

	String tranferProfileToHRExecutive();

}
