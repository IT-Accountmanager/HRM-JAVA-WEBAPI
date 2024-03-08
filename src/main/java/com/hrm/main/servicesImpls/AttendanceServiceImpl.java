package com.hrm.main.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrm.main.models.Attendance;
import com.hrm.main.models.Employee;
import com.hrm.main.models.Helper.EnumCollection.AttendanceStatus;
import com.hrm.main.payloads.ApplyLeaveDto;
import com.hrm.main.payloads.AttendanceEmployeeDto;
import com.hrm.main.repositories.IAttendanceRepository;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.services.IAttendanceService;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	@Override
	public String clockIn(String employeeId, LocalDate date) {
	    // Check if the employee has already clocked in for the specified date
	    Attendance existingAttendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

	    if (existingAttendance != null) {
	        return "Clock-in record already exists for Employee Id : " + employeeId + " on " + date;
	    } else {
	        // Get the current time
	        LocalTime currentTime = LocalTime.now();

	        // Create a new attendance record
	        Attendance attendance = new Attendance();
	        attendance.setEmployeeId(employeeId);
	        attendance.setMonth(LocalDateTime.now().getMonth());
	        attendance.setDate(date);
	        attendance.setInTime(currentTime);
	        attendance.setAttendanceStatus(AttendanceStatus.Present);

	        // Save the attendance record
	        attendanceRepository.save(attendance);

	        return "Attendance added for Employee Id : " + employeeId + " on " + date;
	    }
	}


	@Override
	public String clockOut(String employeeId) {

		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);

		attendance.setOutTime(LocalTime.now());
		attendance.setWorkHrs(Duration.between(attendance.getOutTime(), attendance.getInTime()));
		this.attendanceRepository.save(attendance);
		return "Check Out of Employee Id " + employeeId + " at " + attendance.getOutTime();

	}

	@Override
	public List<Attendance> allAttendance(String employeeId) {
		List<Attendance> allByEmployeeId = attendanceRepository.findAllByEmployeeId(employeeId);

		if (allByEmployeeId.isEmpty()) {

			return Collections.emptyList();
		}

		return allByEmployeeId;
	}

	@Override
	public AttendanceEmployeeDto getAttendance(String employeeId) {
		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		AttendanceEmployeeDto dto = new AttendanceEmployeeDto();

		dto.setEmployeeId(employeeId);
		dto.setMonth(attendance.getMonth());
		dto.setDate(attendance.getDate());
		dto.setInTime(attendance.getInTime());
		dto.setOutTime(attendance.getOutTime());
		dto.setWorkHours(dto.calculateWorkHours());
		dto.setWorkHrs(dto.formatWorkHours());
		dto.setAttendanceStatus(attendance.getAttendanceStatus());
		dto.setManager(employee.getManager());
		dto.setProjectId(null);
		dto.setAppliedHoursForBilling(null);
		dto.setApprovedHoursForBilling(null);
		dto.setBillingHoursStatus(null);

		return dto;
	}

	@Override
	public String deleteAttendance(Integer id) {
		try {
			attendanceRepository.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

	@Override
	public String editAttendance(Attendance attendance, Integer id) {

		try {
			if (this.attendanceRepository.existsById(id)) {
				// attendance.setId(id);
				this.attendanceRepository.save(attendance);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public ApplyLeaveDto createOrUpdateLeave(ApplyLeaveDto leaveDto) {
	    if (leaveDto.getEmployeeId() == null) {
	        throw new IllegalArgumentException("Employee ID cannot be null.");
	    }
	    
	    String employeeId = leaveDto.getEmployeeId();
	    Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
	    if (attendance == null) {
	        throw new IllegalStateException("Attendance record not found for employee: " + employeeId);
	    }
	    
	    
	    if (!"P".equals(attendance.getAttendanceStatus()) && 
	            !"Weekoff".equals(attendance.getAttendanceStatus()) && 
	            !"Holiday".equals(attendance.getAttendanceStatus())) {

	      
	        leaveDto.setStartDate(leaveDto.getStartDate());
	        leaveDto.setEndDate(leaveDto.getEndDate());
	        leaveDto.setLeaveType(leaveDto.getLeaveType());
	        leaveDto.setLeaveReason(leaveDto.getLeaveReason());
	        leaveDto.setHalf1(leaveDto.getHalf1());
	        leaveDto.setHalf2(leaveDto.getHalf2());
	        
	        

	        // Save or update the attendance record
//	        attendanceRepository.save(attendance);
	        
	        // Update the status in the attendance record
//	        attendance.setAttendanceStatus("Pending");
	        attendanceRepository.save(attendance);
	        return leaveDto;
	    } else {
	        return null;
	    }
	}





//	@Override
//	public ApplyLeaveDto getLeave(String employeeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
