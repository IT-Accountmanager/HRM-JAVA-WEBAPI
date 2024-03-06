package com.hrm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.Attendance;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Integer> {

	Attendance findByEmployeeId(String employeeId);

	Attendance findByEmployeeIdAndDate(String employeeId, LocalDate now);

	List<Attendance> findAllByEmployeeId(String employeeId);

}

