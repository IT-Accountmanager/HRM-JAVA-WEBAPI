package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.OnboardingEmployeeSummary;
import com.hrm.main.repositories.IOnboardingEmployeeSummaryRepository;
import com.hrm.main.services.IOnboardingEmployeeSummaryService;

@Service

public class OnboardingEmployeeSummaryServiceImpl implements IOnboardingEmployeeSummaryService {
	
	@Autowired
	private IOnboardingEmployeeSummaryRepository onboardingEmployeeSummaryRepository;

	@Override
	public String createSummary(OnboardingEmployeeSummary summary) {
		try {
			var employeeSummary =this.onboardingEmployeeSummaryRepository.save(summary);
			if (employeeSummary.getId() > 0) {
				return "Employee summary details is added : " + employeeSummary.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "Employee summary details is not added : ";
	}

	@Override
	public List<OnboardingEmployeeSummary> getAllSummary() {
		List<OnboardingEmployeeSummary> allSummary = onboardingEmployeeSummaryRepository.findAll();
		return allSummary;
	}

	@Override
	public OnboardingEmployeeSummary getSummaryById(int id) {
		OnboardingEmployeeSummary employeeSummary = onboardingEmployeeSummaryRepository.findById(id).get();
		return employeeSummary;
	}

	/*
	 * @Override public String updateSummary(OnboardingEmployeeSummary summary,
	 * Integer id) { try {
	 * if(this.onboardingEmployeeSummaryRepository.existsById(id)) {
	 * 
	 * summary.setId(id);
	 * 
	 * this.onboardingEmployeeSummaryRepository.save(summary);
	 * 
	 * return "Id no. : " + id +" is updated .";
	 * 
	 * }else { return "Id no. : "+id+ " is does not exists ."; }
	 * 
	 * } catch (Exception e) { e.getMessage(); } return "Id no. : " + id +
	 * " is not updated ."; }
	 */

	/*
	 * @Override public String deleteSummary(Integer id) { try {
	 * onboardingEmployeeSummaryRepository.deleteById(id);
	 * 
	 * return "Id no. : " + id +" is deleted";
	 * 
	 * } catch (Exception e) { e.getMessage(); } return "Id no. : " + id
	 * +" is not deleted" ; }
	 */
}
