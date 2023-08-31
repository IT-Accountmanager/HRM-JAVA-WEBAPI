package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.OnboardingHRExecutive;
import com.hrm.main.repositories.IOnboardingHRExecutiveRepository;
import com.hrm.main.services.IOnboardingHRExecutiveService;

@Service

public class OnboardingHRExecutiveServiceImpl implements IOnboardingHRExecutiveService {
	
	@Autowired
	private IOnboardingHRExecutiveRepository onboardingHRExecutiveRepository;

	@Override
	public String createExecutive(OnboardingHRExecutive hrExecutive) {
		try {
			var executive1 = this.onboardingHRExecutiveRepository.save(hrExecutive);
			if(executive1.getId()>0) {
				return "All details are added : " + executive1.getId();
			}
		} catch (Exception e) {
			e.getMessage();			
		}
		return "All details are not added : ";
	}

	@Override
	public List<OnboardingHRExecutive> getAllExecutive() {
		List<OnboardingHRExecutive> allExecutive = onboardingHRExecutiveRepository.findAll();
		return allExecutive;
	}

	@Override
	public OnboardingHRExecutive getExecutiveById(int id) {
		OnboardingHRExecutive hr = onboardingHRExecutiveRepository.findById(id).get();
		return hr;
	}

	@Override
	public String updateHRExecutive(OnboardingHRExecutive hrExecutive, Integer id) {
		try {
			
			if(this.onboardingHRExecutiveRepository.existsById(id)) {			
			
			hrExecutive.setId(id);
			
			this.onboardingHRExecutiveRepository.save(hrExecutive);
			
			return "Id no. : " +id+ " is updated.";
			
			}else {
				return "Id no. : "+id+" is does not exists";
			}
		}catch (Exception e) {
			e.getMessage();
		}
		return "Id no. : " +id+ "is not updated.";
	}

	@Override
	public String deleteHrExecutive(Integer id) {
		try {
			onboardingHRExecutiveRepository.deleteById(id);
			
			return "Id no. : " + id +" is deleted";
			
		}catch (Exception e) {
			e.getMessage();
		}
		return "Id no. : " + id +" is not deleted" ;
	}
	

}
