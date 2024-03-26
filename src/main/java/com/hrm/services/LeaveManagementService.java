package com.hrm.services;

import com.hrm.payloads.ApplyLeaveDto;

public interface LeaveManagementService {
	
	String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId);
	
	ApplyLeaveDto getLeave(String employeeId);
	
	

}
