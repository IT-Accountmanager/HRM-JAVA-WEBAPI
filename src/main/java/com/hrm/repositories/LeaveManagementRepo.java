package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.LeaveManagementTable;


@Repository
public interface LeaveManagementRepo extends JpaRepository<LeaveManagementTable, Integer>{

	LeaveManagementTable findByEmployeeId(String employeeId);

	

}
