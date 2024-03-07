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
import com.hrm.main.payloads.AttendanceHRManagerDto;
import com.hrm.main.payloads.BillableHoursDto;
import com.hrm.main.payloads.RegularizationHoursDto;
import com.hrm.main.repositories.IAttendanceRepository;
import com.hrm.main.repositories.IEmployeeRepository;
import com.hrm.main.services.IAttendanceService;

import jakarta.transaction.Transactional;

@Service

public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	private IAttendanceRepository attendanceRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public String clockIn(String employeeId, LocalDate date) {

//        	 // Define the desired date format
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//            // Format the date
//            String formattedDate = date.format(dateFormatter);

		// Define the desired time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		// Get the current time in the desired format
		String formattedTime = LocalTime.now().format(formatter);

		// Parse the formatted time back to LocalTime if needed
		LocalTime parsedTime = LocalTime.parse(formattedTime, formatter);

		// Check if the employee has already clocked in for the specified date
		Attendance existingAttendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (existingAttendance != null) {
			return "Clock-in record already exists for Employee Id : " + employeeId + " on " + date;
		} else {
			// Create a new attendance record
			Attendance attendance = new Attendance();
			attendance.setEmployeeId(employeeId);
			attendance.setMonth(LocalDateTime.now().getMonth());
			attendance.setDate(date);
			attendance.setInTime(parsedTime);
			attendance.setAttendanceStatus(AttendanceStatus.Present);

			// Save the attendance record
			attendanceRepository.save(attendance);

			return "Attendance added for Employee Id : " + employeeId + " on " + date;
		}
	}

	@Override
	public String clockOut(String employeeId) {
		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);

		LocalTime currentTime = LocalTime.now();
		attendance.setOutTime(currentTime);

		// Define the desired time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		// Format the out time to HH:mm format
		String formattedOutTime = currentTime.format(formatter);

		// Calculate work hours and set it
		Duration workDuration = Duration.between(attendance.getOutTime(), attendance.getInTime());
		attendance.setWorkHrs(workDuration);

		// Save changes to the attendance record
		this.attendanceRepository.save(attendance);

		// Return a message with the check-out time
		return "Checked out Employee Id " + employeeId + " at " + formattedOutTime;
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
	public ApplyLeaveDto addLeave(ApplyLeaveDto leaveDto) {
		if (leaveDto.getEmployeeId() == null) {
			throw new IllegalArgumentException("Employee ID cannot be null.");
		}

		String employeeId = leaveDto.getEmployeeId();
		Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
		if (attendance == null) {
			throw new IllegalStateException("Attendance record not found for employee: " + employeeId);
		}

		if (!"P".equals(attendance.getAttendanceStatus()) && !"Weekoff".equals(attendance.getAttendanceStatus())
				&& !"Holiday".equals(attendance.getAttendanceStatus())) {

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

	 @Override
	    public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
		    LocalDate date = billableHoursDto.getDate();
	        float productionHours = billableHoursDto.getProductionHours();
	        float otherHours = billableHoursDto.getOtherHours();
	        
	        
	        float totalHours = productionHours + otherHours;

	        
	        LocalDate date1 = LocalDate.now();

	        Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date1);

	        if (attendance != null) {
	            
	            attendance.setProductionHours(productionHours);
	            attendance.setOtherHours(otherHours);
	            attendance.setTotalHours(totalHours);

	            attendanceRepository.save(attendance);
	            return "Billable hours added for employee " + employeeId + " on " + date1;
	        } else {
	            return "Attendance record not found for employee " + employeeId + " on " + date1;
	        }
	    }

	 @Override
	 public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
		 LocalDate date = regularizationHoursDto.getDate();
	     LocalTime exactInTime = regularizationHoursDto.getExactInTime();
	     LocalTime exactOutTime = regularizationHoursDto.getExactOutTime();
	     String regularisationReason = regularizationHoursDto.getRegularisationReason();
	     
	     
	     Duration difference = Duration.between(exactInTime, exactOutTime);
	     
	     // Define the threshold duration of 9 hours and 30 minutes
	     Duration threshold = Duration.ofHours(9).plusMinutes(30);

	     Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);

	     if (attendance != null) {
	    	 attendance.setDate(date);
	         attendance.setExactInTime(exactInTime);
	         attendance.setExactOutTime(exactOutTime);
	         attendance.setRegularisationReason(regularisationReason);
	         
	         // Check if the duration is less than the threshold
	         if (difference.compareTo(threshold) < 0) {
	             // If less, set RegularisationRequestHours to the difference from the threshold
	             Duration remainingTime = threshold.minus(difference);
	             attendance.setRegularisationRequestHours(remainingTime);
	         } else {
	             // If greater or equal, set RegularisationRequestHours to zero
	             attendance.setRegularisationRequestHours(Duration.ZERO);
	         }

	         attendanceRepository.save(attendance);
	         return "Regularization hours added for employee " + employeeId;
	     } else {
	         return "Attendance record not found for employee " + employeeId;
	     }
	 }


	
//	 @Override
//	 public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
//	     LocalTime exactInTime = regularizationHoursDto.getExatctInTime();
//	     LocalTime exactOutTime = regularizationHoursDto.getExactOutTime();
//
//	     // Calculate the duration between exactInTime and exactOutTime
//	     Duration difference = Duration.between(exactInTime, exactOutTime);
//
//	     // Convert total minutes to hours and remaining minutes
//	     long totalMinutes = difference.toMinutes();
//	     long regularHoursThresholdMinutes = 9 * 60 + 30;
//
//	     if (totalMinutes < regularHoursThresholdMinutes) {
//	         long regularizedMinutes = regularHoursThresholdMinutes - totalMinutes;
//	         long regularizedHours = regularizedMinutes / 60;
//	         long remainingMinutes = regularizedMinutes % 60;
//
//	         // Format regularized hours and remaining minutes in HH:MM format
//	         String regularizedTime = String.format("%02d:%02d", regularizedHours, remainingMinutes);
//
//	         // Create a new Attendance entity
//	         Attendance attendance = new Attendance();
//	         attendance.setEmployeeId(employeeId);
//	         attendance.setRegularisationRequestHours(regularizedTime);
//
//	         // Save the new attendance record
//	         attendanceRepository.save(attendance);
//
//	         return "Regularization hours added for employee " + employeeId + ": " + regularizedTime;
//	     } else {
//	         return "Regularized hours not applicable for employee " + employeeId;
//	     }
//	 }


	


//	@Override
//	public float gettotalHoursFromBillableHoursDto(String employeeId) {
//		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
//
//		float totalHours = attendance.getTotalHours();
//		return totalHours;
//	}
	
//	@Override
//	public LocalTime getinTimeFromAttendance(String employeeId) {
//		Attendance attendance =this.attendanceRepository.findByEmployeeId(employeeId);
//		LocalTime inTime = attendance.getInTime();
//		return inTime;
//		
//	}
	
//	@Override
//	public LocalTime getoutTimeFromAttendance(String employeeId) {
//		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
//		LocalTime outTime = attendance.getOutTime();
//		return outTime;
//	}
	
	
	



}
