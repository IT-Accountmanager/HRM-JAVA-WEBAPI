//package com.hrm.scheduler;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.hrm.helper.EnumCollection.LeaveType;
//import com.hrm.models.Employee;
//import com.hrm.models.LeaveSummary;
//import com.hrm.repositories.IEmployeeRepository;
//import com.hrm.repositories.LeaveManagementRepo;
//
//
//
//@Component
//
//public class LeaveScheduler {
//	
//        @Autowired
//	    private IEmployeeRepository employeeRepository;
//
//	    @Autowired
//	    private LeaveManagementRepo leaveManagementRepo;
//
//	    @Scheduled(cron = "0 0 0 1 * *")
//	    public void creditLeaveDays() {
//	        LocalDate currentDate = LocalDate.now();
//	        if (currentDate.getDayOfMonth() == 1) { 
//	            List<Employee> employees = employeeRepository.findAll();
//	            for (Employee employee : employees) {
//	                // Credit 1.5 leave days to each employee
//	                double leaveDaysToAdd = 1.5;
//	                LeaveSummary leaveSummary = new LeaveSummary(employee, leaveDaysToAdd, LeaveType.Earned_Leave, currentDate);
//	                leaveManagementRepo.save(leaveSummary); // Save the leave information
//	            }
//	            System.out.println("Leave days credited to all employees on " + currentDate);
//	        }
//	    }
//	}
//
//

