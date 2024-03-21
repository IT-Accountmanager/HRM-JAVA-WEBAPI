package com.hrm.scheduler;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hrm.models.Attendance;
import com.hrm.repositories.IAttendanceRepository;
import com.hrm.repositories.IHolidayRepository;
@Component
public class AttendanceScheduler {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IHolidayRepository holidayRepository;

	private boolean isClockInPresent = true;
	private boolean isClockOutPresent = true;
	private boolean isComboOff = false;

	@Scheduled(fixedRate = 1 ,timeUnit = TimeUnit.MINUTES)
	public void performAttendanceStatus() {
		LocalDate date = LocalDate.now();
		List<Attendance> employeesTodaysAttendance = attendanceRepository.findAllByDate(date);

		for (Attendance todaysAttendance : employeesTodaysAttendance) {
			LocalTime inTime = todaysAttendance.getInTime();
			LocalTime outTime = todaysAttendance.getOutTime();
			char attendanceResult = calculateAttendanceResult(inTime, outTime);
			todaysAttendance.setAttendanceStatus(attendanceResult);
			this.attendanceRepository.save(todaysAttendance);
		}

	}

	private char calculateAttendanceResult(LocalTime inTime, LocalTime outTime) {
		if (isClockInPresent && !isClockOutPresent) {
			return 'A'; // Anomaly
		} else if (isClockInPresent && isClockOutPresent) {
			long timeDifference = 9 * 60; // Assuming the time difference is in minutes
			if (calculateTimeDifference(inTime, outTime) <= timeDifference) {
				return 'S'; // Very short time between clock in and clock out
			} else if (calculateTimeDifference(inTime, outTime) >= 10 * 60) {
				return 'P';
			}
		} else if (!isClockInPresent && !isClockOutPresent) {
			if (isHoliday()) {
				return 'H'; // Holiday
			} else if (isComboOff) {
				return 'C'; // Combo off
			} else if (isWeakOff()) {
				return 'W'; // Weak off
			} else {
				return 'X'; // Default
			}
		}
		return 'X'; // Default case
	}

	private long calculateTimeDifference(LocalTime inTime, LocalTime outTime) {
		Duration duration = Duration.between(inTime, outTime);
		return duration.toMinutes();
	}

	private boolean isHoliday() {
		LocalDate currentDate = LocalDate.now();
		boolean isHoliday = holidayRepository.existsByDate(currentDate);
		return isHoliday;
	}

	private boolean isWeakOff() {
		LocalDate currentDate = LocalDate.now();
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
		return isWeekend;
	}
}
