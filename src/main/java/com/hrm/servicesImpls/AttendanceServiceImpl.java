package com.hrm.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.helper.EnumCollection.AttendanceStatus;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.models.Attendance;
import com.hrm.models.Employee;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.repositories.IAttendanceRepository;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.services.IAttendanceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(IAttendanceService.class);
	
	
	


	@Override
	public String clockIn(String employeeId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    String formattedTime = LocalTime.now().format(formatter);

	
	    LocalTime parsedTime = LocalTime.parse(formattedTime, formatter);

		Attendance existingAttendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, LocalDate.now());

		if (existingAttendance != null) {
			return "Clock-in record already exist for Employee Id : " + employeeId;
		} else {
			Attendance attendance = new Attendance();// new api getclock in timee(emp id , now time and current time)

			attendance.setEmployeeId(employeeId);
			attendance.setMonth(LocalDateTime.now().getMonth());
			attendance.setDate(LocalDate.now());
			
			attendance.setInTime(parsedTime);
			attendance.setAttendanceStatus(AttendanceStatus.Present);
			this.attendanceRepository.save(attendance);
			return "attendence added of Employee Id : " + employeeId;
		}

	}

	

	@Override
	public String clockOut(String employeeId) {
	    Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
	    LocalTime outTime = LocalTime.now();
	    
	    // Format the out time to HH:MM format
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    String formattedOutTime = outTime.format(formatter);
	    
	    attendance.setOutTime(outTime);
	    Duration workDuration = Duration.between(attendance.getInTime(), outTime);
	    
	    long nanoseconds = workDuration.toNanos();
	    double hours = nanoseconds / (double) Duration.ofHours(1).toNanos();
	    
	    attendance.setWorkHrs(Duration.ofHours((long) hours));
	    
	    this.attendanceRepository.save(attendance);
	    
	    return "Check Out of Employee Id " + employeeId + " at " + formattedOutTime;
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
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
		AttendanceEmployeeDto dto = new AttendanceEmployeeDto();

		try {

			logger.info("Attempting to retrieve attendance for employee with ID: {}", employeeId);

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

			logger.info("Attendance retrieved successfully for employee with ID: {}", employeeId);

			return dto;
		} catch (Exception e) {
			logger.error("An error occurred while processing attendance for employee with ID: {}", employeeId, e);

			// Optionally, you can rethrow the exception or return a default/empty DTO
			throw new RuntimeException("Error retrieving attendance information for employee with ID: " + employeeId,
					e);
		}
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

//	@Override
//<<<<<<< HEAD
//	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
//		List<Attendance> attendanceListOfEmployee = this.attendanceRepository.findAllByEmployeeId(employeeId);
//		List<LocalDate> listOfDate = billableHoursDto.getListOfDate();
//
//		for (LocalDate date : listOfDate) {
//
//		}
//
//		return null;
//	}

//	@Override
//	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
//		return null;
//	}

//=======
	
	
	
	
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
	         
	         long nanoseconds = difference.toNanos();
	         double hours = nanoseconds / (double) Duration.ofHours(1).toNanos();
	         
	         
	         
	         if (difference.compareTo(threshold) < 0) {
	            
	             Duration remainingHours = threshold.minus(difference);
	             attendance.setRegularisationRequestHours(remainingHours);
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

//	@Override
//	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId) {
	   
	    LeaveType leaveType = applyLeaveDto.getLeaveType();
	    LocalDate startDate = applyLeaveDto.getStartDate();
	    LocalDate endDate = applyLeaveDto.getEndDate();
	    Half half1 = applyLeaveDto.getHalf1();
	    Half half2 = applyLeaveDto.getHalf2();
	    String leaveReason = applyLeaveDto.getLeaveReason();
	    
	  
	    Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
	    if (attendance == null) {
	        attendance = new Attendance();
	        attendance.setEmployeeId(employeeId);
	    }
	    
	    attendance.setLeaveType(leaveType);
	    attendance.setStartDate(startDate);
	    attendance.setEndDate(endDate);
	    attendance.setHalf1(half1);
	    attendance.setHalf2(half2);
	    attendance.setLeaveReason(leaveReason);
	   
	    attendanceRepository.save(attendance);
	    
	    return "Leave Added Successfully for employee Id " + employeeId;
	}

	 
	 
	 
//		@Override
//		public float gettotalHoursFromBillableHoursDto(String employeeId) {
//			Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
	//
//			float totalHours = attendance.getTotalHours();
//			return totalHours;
//		}
		
//		@Override
//		public LocalTime getinTimeFromAttendance(String employeeId) {
//			Attendance attendance =this.attendanceRepository.findByEmployeeId(employeeId);
//			LocalTime inTime = attendance.getInTime();
//			return inTime;
//			
//		}
		
//		@Override
//		public LocalTime getoutTimeFromAttendance(String employeeId) {
//			Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
//			LocalTime outTime = attendance.getOutTime();
//			return outTime;
//		}
	 
		
//	 ---------------FOR DATE FORMAT MODIFIED CLOCK IN----------------
//	    @Override
//		public String clockIn(String employeeId, LocalDate date) {
//
////	        	 // Define the desired date format
////	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	//
////	            // Format the date
////	            String formattedDate = date.format(dateFormatter);
//
//			// Define the desired time format
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//
//			// Get the current time in the desired format
//			String formattedTime = LocalTime.now().format(formatter);
//
//			// Parse the formatted time back to LocalTime if needed
//			LocalTime parsedTime = LocalTime.parse(formattedTime, formatter);
//
//			// Check if the employee has already clocked in for the specified date
//			Attendance existingAttendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
//
//			if (existingAttendance != null) {
//				return "Clock-in record already exists for Employee Id : " + employeeId + " on " + date;
//			} else {
//				// Create a new attendance record
//				Attendance attendance = new Attendance();
//				attendance.setEmployeeId(employeeId);
//				attendance.setMonth(LocalDateTime.now().getMonth());
//				attendance.setDate(date);
//				attendance.setInTime(parsedTime);
//				attendance.setAttendanceStatus(AttendanceStatus.Present);
//
//				// Save the attendance record
//				attendanceRepository.save(attendance);
//
//				return "Attendance added for Employee Id : " + employeeId + " on " + date;
//			}
//		}
	 
	 
//	 -------------------------FOR DATE FORMAT MODIFIED CLOCKOUT----------------
//	 @Override
//		public String clockOut(String employeeId) {
//			Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
//
//			LocalTime currentTime = LocalTime.now();
//			attendance.setOutTime(currentTime);
//
//			// Define the desired time format
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//
//			// Format the out time to HH:mm format
//			String formattedOutTime = currentTime.format(formatter);
//
//			// Calculate work hours and set it
//			Duration workDuration = Duration.between(attendance.getOutTime(), attendance.getInTime());
//			attendance.setWorkHrs(workDuration);
//
//			// Save changes to the attendance record
//			this.attendanceRepository.save(attendance);
//
//			// Return a message with the check-out time
//			return "Checked out Employee Id " + employeeId + " at " + formattedOutTime;
//		}//


//	@Override
//	public ApplyLeaveDto getLeave(String employeeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//>>>>>>> branch 'ramachandra' of https://github.com/IT-Accountmanager/HRM-JAVA-WEBAPI.git

}
