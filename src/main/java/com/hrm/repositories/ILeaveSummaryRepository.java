package com.hrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.hrm.models.LeaveSummary;

@Service
public interface ILeaveSummaryRepository extends JpaRepository<LeaveSummary, Integer> {

	@Query(value = "SELECT l.employee_id " + " FROM leave_summary l" + "", nativeQuery = true)
	List<String> getAllEmployeeList();

	LeaveSummary findByEmployeeId(String employeeId);

}
