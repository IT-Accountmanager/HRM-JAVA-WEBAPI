package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Attendance;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Integer> {

	Attendance findByEmployeeId(String employeeId);

}
