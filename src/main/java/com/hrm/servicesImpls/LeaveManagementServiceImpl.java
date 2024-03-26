package com.hrm.servicesImpls;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.models.LeaveManagementTable;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.LeaveManagementRepo;
import com.hrm.services.LeaveManagementService;

@Service

public class LeaveManagementServiceImpl implements LeaveManagementService{
	@Autowired
	LeaveManagementRepo leaveManagementRepo;
	
	@Autowired
	IEmployeeRepository employeeRepository;


	@Override
	public String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId) {
		
		    LeaveType leaveType = applyLeaveDto.getLeaveType();
		    LocalDate startDate = applyLeaveDto.getLeaveStartDate();
		    LocalDate endDate = applyLeaveDto.getLeaveEndDate();
		    Half half1 = applyLeaveDto.getHalf1();
		    Half half2 = applyLeaveDto.getHalf2();
		    String leaveReason = applyLeaveDto.getLeaveReason();
	
		    LeaveManagementTable leaveManagementTable = this.leaveManagementRepo.findByEmployeeId(employeeId);
		    if (leaveManagementTable == null) {
		        leaveManagementTable = new LeaveManagementTable();
		        leaveManagementTable.setEmployeeId(employeeId);
		    }
	
		    leaveManagementTable.setLeaveType(leaveType);
		    leaveManagementTable.setLeaveStartDate(startDate);
		    leaveManagementTable.setLeaveEndDate(endDate);
		    leaveManagementTable.setHalf1(half1);
		    leaveManagementTable.setHalf2(half2);
		    leaveManagementTable.setLeaveReason(leaveReason);
	
		
	
		    // Fetch the month from start date
		    Month startMonth = startDate.getMonth();
		    // Fetch the month from end date
		    Month endMonth = endDate.getMonth();
		    
		    // Calculate the number of days between start date and end date
		    long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
	
		    leaveManagementRepo.save(leaveManagementTable);
	
		    return "Leave Added Successfully for employee Id " + employeeId + ". Number of days: " + numberOfDays +
		            ". Start Month: " + startMonth + ". End Month: " + endMonth;
		}
	

	@Override
	public ApplyLeaveDto getLeave(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
