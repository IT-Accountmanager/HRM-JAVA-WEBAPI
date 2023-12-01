package com.hrm.main.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Employee;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.repositories.IOnboardingRepository;
import com.hrm.main.services.ISummaryService;

@Service
public class SummaryServiceImpl implements ISummaryService {

	@Autowired
	IOnboardingRepository onboardingRepository;
	@Autowired
	IEmployeeRepository employeeRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<SummaryDto> getAll() {

		List<Employee> findAll = this.employeeRepository.findAll();
		List<SummaryDto> summaryDtoList = new ArrayList<>();

		for (Employee employee : findAll) {
			long candidateId = employee.getCandidateId();
			Onboarding onboarding = this.onboardingRepository.findByCandidateId(candidateId);

			SummaryDto summaryDto = new SummaryDto();

			summaryDto.setEmployeeId(employee.getEmployeeId());
			summaryDto.setEmployeeName(employee.getName());
			summaryDto.setMobileNumber(onboarding.getContactNumber());
			summaryDto.setEmailId(onboarding.getEmailId());
			summaryDto.setDateOfJoining(onboarding.getDateOfJoining());
			summaryDto.setBondBreakAmount(onboarding.getBondBreakAmount());
//summaryDto.setRelevantExperience();
			summaryDto.setDesignation(employee.getDesignation());
//summaryDto.setDepartment();
			summaryDto.setStatus(onboarding.getCandidatesStatus());

			summaryDtoList.add(summaryDto);
		}

		return summaryDtoList;
	}

}
