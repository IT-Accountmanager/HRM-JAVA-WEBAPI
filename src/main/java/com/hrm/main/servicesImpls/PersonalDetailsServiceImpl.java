package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.PersonalDetails;
import com.hrm.main.repositories.IPersonalDetailsRepository;
import com.hrm.main.services.IPersonalDetailsService;

@Service
public class PersonalDetailsServiceImpl implements IPersonalDetailsService {

	@Autowired
	IPersonalDetailsRepository personalDetailsRepo;

	@Override
	public String addPersonalDetails(PersonalDetails personalDetails) {

		PersonalDetails details = personalDetailsRepo.save(personalDetails);

		return "Personal Details and Image Added Successfully";
	}

	@Override	
	public PersonalDetails uploadImage(String firstName,String middleName,String lastName,String dateOfBirth,String gender,String bloodGroup,String maritalStatus,String officialMailId, byte[]imageData) {
		PersonalDetails personaldetails =new PersonalDetails(null, firstName, middleName, lastName, dateOfBirth, gender, bloodGroup, maritalStatus, officialMailId, imageData, officialMailId, null, null);
		personaldetails.setFirstName(firstName);
		personaldetails.setMiddleName(middleName);
		personaldetails.setLastName(lastName);
		personaldetails.setDateOfBirth(dateOfBirth);
		personaldetails.setGender(gender);
		personaldetails.setBloodGroup(bloodGroup);
		personaldetails.setMaritalStatus(maritalStatus);
		personaldetails.setOfficialMailId(officialMailId);
		personaldetails.setImageData(imageData);
		return personalDetailsRepo.save(personaldetails);
		
	
	}

}
