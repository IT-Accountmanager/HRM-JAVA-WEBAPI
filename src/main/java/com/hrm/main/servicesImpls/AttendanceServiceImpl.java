package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.Attendance;
import com.hrm.main.repositories.IAttendanceRepository;
import com.hrm.main.services.IAttendanceService;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepo;

	@Override
	public String addAttendence(Attendance attendance) {
		try {

			Attendance addedAttendence = this.attendanceRepo.save(attendance);
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
	public List<Attendance> allAttendance() {
		return this.attendanceRepo.findAll();

	}

	@Override
	public Attendance getAttendance(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAttendance(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editAttendance(Attendance attendance, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
