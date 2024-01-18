package com.hrm.main.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Attendance;
import com.hrm.main.models.Helper.EnumCollection.AttendanceStatus;
import com.hrm.main.repositories.IAttendanceRepository;
import com.hrm.main.services.IAttendanceService;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepo;

	@Override
	public String clockIn(String employeeId) {
		Attendance attendance = new Attendance();
		attendance.setEmployeeId(employeeId);
		attendance.setInTime(LocalTime.now());
		attendance.setMonth(LocalDateTime.now().getMonth());
		attendance.setDate(LocalDate.now());
		attendance.setAttendanceStatus(AttendanceStatus.Present);
		this.attendanceRepo.save(attendance);
		return "attendence added of Employee Id : " + employeeId;
	}

	@Override
	public String clockOut(String employeeId) {
		Attendance attendance = this.attendanceRepo.findByEmployeeId(employeeId);
		attendance.setOutTime(LocalTime.now());
		attendance.setWorkHrs(Duration.between(attendance.getOutTime(), attendance.getInTime()));
		this.attendanceRepo.save(attendance);
		return "Check Out of Employee Id " + employeeId + " at " + attendance.getOutTime();
	}

	@Override
	public List<Attendance> allAttendance() {
		return this.attendanceRepo.findAll();

	}

	@Override
	public Attendance getAttendance(String employeeId) {
		Attendance attendance = this.attendanceRepo.findByEmployeeId(employeeId);
		return attendance;
	}

	@Override
	public String deleteAttendance(Integer id) {
		try {
			attendanceRepo.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

	@Override
	public String editAttendance(Attendance attendance, Integer id) {

		try {
			if (this.attendanceRepo.existsById(id)) {
				// attendance.setId(id);
				this.attendanceRepo.save(attendance);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

}
