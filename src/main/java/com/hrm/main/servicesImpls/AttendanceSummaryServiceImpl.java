package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.AttendanceSummary;
import com.hrm.main.repositories.IAttendanceSummaryRepository;
import com.hrm.main.services.IAttendanceSummaryService;

@Service
public class AttendanceSummaryServiceImpl implements IAttendanceSummaryService {

	@Autowired
	IAttendanceSummaryRepository attendanceRepo;

	@Override
	public String addAttendence(AttendanceSummary attendance) {
		try {

			AttendanceSummary addedAttendence = this.attendanceRepo.save(attendance);
			if (addedAttendence.getId() > 0) {
				return "Attendance of Id no. " + addedAttendence.getId() + " is Successfully Added!";
			}
			return "Attendance is not Added!";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Attendance is not added!";

	}

	@Override
	public List<AttendanceSummary> allAttendance() {
		return this.attendanceRepo.findAll();

	}

	@Override
	public AttendanceSummary getAttendance(Integer id) {
		AttendanceSummary attendance = this.attendanceRepo.findById(id).get();
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
	public String editAttendance(AttendanceSummary attendance, Integer id) {
		try {
			attendance.setId(id);

			attendanceRepo.save(attendance);

			return "Id no. " + id + " is updated. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";

	}

}
