package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.models.EmployeeWorkWeek;

public interface EmployeeWorkWeekRepo extends JpaRepository<EmployeeWorkWeek, Integer>{

	EmployeeWorkWeek findByEmployeeId(String employeeId);

}
