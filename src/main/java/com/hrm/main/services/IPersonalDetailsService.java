package com.hrm.main.services;

import com.hrm.main.models.PersonalDetails;

public interface IPersonalDetailsService {

	String addPersonalDetails(PersonalDetails personalDetails);

	//void uploadImage(String imageName, byte[] bytes);

	PersonalDetails uploadImage(String firstName, String middleName, String lastName, String dateOfBirth, String gender,
			String bloodGroup, String maritalStatus, String officialMailId, byte[] imageData);

}
