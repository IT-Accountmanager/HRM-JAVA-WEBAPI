package com.hrm.servicesImpls;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.helper.EnumCollection.ShiftRule;
import com.hrm.helper.EnumCollection.WeekRule;
import com.hrm.models.EmployeeWorkWeek;
import com.hrm.payloads.EmployeeWorkWeekDto;
import com.hrm.repositories.EmployeeWorkWeekRepo;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.services.EmployeeWorkWeekService;

@Service

public class EmployeeWorkWeekServiceImpl implements EmployeeWorkWeekService {
	@Autowired
	EmployeeWorkWeekRepo employeeWorkWeekRepo;

	@Autowired
	IEmployeeRepository employeeRepository;

	@Override
	public String addWorkWeek(EmployeeWorkWeekDto employeeWorkWeekDto, String employeeId) {
		String name = employeeWorkWeekDto.getName();
		ShiftRule employeeShiftRule = employeeWorkWeekDto.getShiftRuleSetting();
		WeekRule employeeWeekRule = employeeWorkWeekDto.getWeekRuleSetting();
		LocalDate effectiveDate = employeeWorkWeekDto.getEffectiveDate();

		// Check if the work week entry already exists for the employee
		EmployeeWorkWeek existingWorkWeek = this.employeeWorkWeekRepo.findByEmployeeId(employeeId);

		if (existingWorkWeek != null) {
			// If the work week entry exists, update its properties
			existingWorkWeek.setName(name);
			existingWorkWeek.setShiftRuleSetting(employeeShiftRule);
			existingWorkWeek.setWeekRuleSetting(employeeWeekRule);
			existingWorkWeek.setEffectiveDate(effectiveDate);
			employeeWorkWeekRepo.save(existingWorkWeek);
			return "Employee work week updated successfully for employeeId: " + employeeId;
		} else {
			// If the work week entry does not exist, create a new one and save it
			EmployeeWorkWeek newWorkWeek = new EmployeeWorkWeek();
			newWorkWeek.setEmployeeId(employeeId);
			newWorkWeek.setName(name);
			newWorkWeek.setShiftRuleSetting(employeeShiftRule);
			newWorkWeek.setWeekRuleSetting(employeeWeekRule);
			newWorkWeek.setEffectiveDate(effectiveDate);
			employeeWorkWeekRepo.save(newWorkWeek);
			return "Employee work week added successfully for employeeId: " + employeeId;
		}
	}

	@Override
	public EmployeeWorkWeekDto getWorkWeek(String employeeId) {
		EmployeeWorkWeek WorkWeek = employeeWorkWeekRepo.findByEmployeeId(employeeId);

		if (WorkWeek != null) {
			EmployeeWorkWeekDto employeeWorkWeeDto = new EmployeeWorkWeekDto();

//			employeeWorkWeeDto.setEmployeeId(WorkWeek.getEmployeeId());
			employeeWorkWeeDto.setEffectiveDate(WorkWeek.getEffectiveDate());
			employeeWorkWeeDto.setName(WorkWeek.getName());
			employeeWorkWeeDto.setShiftRuleSetting(WorkWeek.getShiftRuleSetting());
			employeeWorkWeeDto.setWeekRuleSetting(WorkWeek.getWeekRuleSetting());

			return employeeWorkWeeDto;
		} else {
			return null;
		}

	}

//	
//	@Override
//	public BillableHoursDto getBillableHours(String employeeId,LocalDate date) {
//		Attendance billableHours = attendanceRepository.findByEmployeeId(employeeId);
//
//		if (billableHours != null) {
//			BillableHoursDto billableHoursDto = new BillableHoursDto();
//
//			billableHoursDto.setProductionHours(billableHours.getProductionHours());
//			billableHoursDto.setOtherHours(billableHours.getOtherHours());
//			billableHoursDto.setAppliedHrsForBilling(billableHours.getAppliedHrsForBilling());
//
//			return billableHoursDto;
//		}
//
//		else {
//			return null;
//		}
//	}

//	@Override
//	public EmployeeWorkWeek addWorkWeek(EmployeeWorkWeek employeeWorkWeek) {
//		EmployeeWorkWeek result = this.addWorkWeek(employeeWorkWeek);
//		return result;
//	}

}
