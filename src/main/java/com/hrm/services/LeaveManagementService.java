package com.hrm.services;

import java.util.List;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.LeaveDetailsRequestDto;
import com.hrm.payloads.LeaveRequestDetailsDto;
import com.hrm.payloads.ManagerLeaveDetailsDto;
import com.hrm.payloads.ManagerLeaveEditDto;

public interface LeaveManagementService {

	String addLeave(ApplyLeaveDto applyLeaveDto);

	List<ManagerLeaveDetailsDto> getLeaveDetails(LeaveDetailsRequestDto leaveDetailsRequestDto);

	String editLeaveRequest(int id, ManagerLeaveEditDto managerLeaveEditDto);

	List<LeaveRequestDetailsDto> getLeaveDetails(Integer Id);

}
