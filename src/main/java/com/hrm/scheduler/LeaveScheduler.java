
package com.hrm.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.models.Employee;
import com.hrm.models.LeaveSummary;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.ILeaveSummaryRepository;
import com.hrm.repositories.LeaveManagementRepo;

@Component
public class LeaveScheduler {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Autowired
	private LeaveManagementRepo leaveManagementRepo;

	@Autowired
	private ILeaveSummaryRepository leaveSummaryRepository;

	private static final Logger logger = LoggerFactory.getLogger(LeaveScheduler.class);

	@Scheduled(cron = "0 0 0 1 * *")
	// @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
	// @Scheduled(cron = "59 23 L * * ?")
	public void creditLeaveDays() {
		System.out.println("Scheduled task is running...");
		logger.info("Leave Schedular starts : " + LocalDateTime.now());

		List<LeaveSummary> findAll = leaveSummaryRepository.findAll();
		for (LeaveSummary leave : findAll) {
			leave.setTotalBalance((float) (leave.getTotalBalance() + 1.5));
			this.leaveSummaryRepository.save(leave);
		}

//		List<Double> collect = findAll.stream().map(leave -> leave.getTotalBalance() + 1.5)
//				.collect(Collectors.toList());
//		LeaveSummary leaveSummary = new LeaveSummary();
//		for (Double leave : collect) {
//			leaveSummary.setTotalBalance((leave).floatValue());
//			this.leaveSummaryRepository.save(leaveSummary);
//		}
		// save in leave summary table

		// Your task logic here
	}
}
