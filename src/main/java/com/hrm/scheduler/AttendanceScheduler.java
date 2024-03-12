package com.hrm.scheduler;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.hrm.repositories.IAttendanceRepository;

public class AttendanceScheduler {

	@Autowired
	IAttendanceRepository attendanceRepository;

	private boolean isClockInPresent = true;
	private boolean isClockOutPresent = true;
	private boolean isComboOff = false;
	private boolean isWeakOff = false;

	@Scheduled(cron = "0 0 2 * * ?")
	public char performAttendanceCheck(LocalTime inTime, LocalTime outTime) {
		char attendanceResult = calculateAttendanceResult(inTime, outTime);
		return attendanceResult;
	}

	private char calculateAttendanceResult(LocalTime inTime, LocalTime outTime) {
		if (isClockInPresent && !isClockOutPresent) {
			return 'A'; // Anomaly
		} else if (isClockInPresent && isClockOutPresent) {
			long timeDifference = 9; // Assuming the time difference is in minutes
			if (calculateTimeDifference(inTime, outTime) <= timeDifference) {
				return 'V'; // Very short time between clock in and clock out
			}
		} else if (!isClockInPresent && !isClockOutPresent) {
			if (isHoliday()) {
				return 'H'; // Holiday
			} else if (isComboOff) {
				return 'C'; // Combo off
			} else if (isWeakOff) {
				return 'W'; // Weak off
			} else {
				return 'X'; // Some other scenario
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

		// boolean isHoliday = holidayRepository.isHoliday(currentDate);

		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

		return /* isHoliday || */ isWeekend;
	}

}
