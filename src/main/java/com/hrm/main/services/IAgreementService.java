package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Agreement;

public interface IAgreementService {
	
	
	  String createAgreement(Agreement agreement);
	  
	  List<Agreement> getAllAgreement();
	  
	  Agreement getAgreementById(int id);
	  
	  String updateAgreement(Agreement agreement, Integer id);
	  
	  String deleteAgreement(Integer id);
	 
}
