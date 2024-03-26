package com.hrm.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrm.models.Attendance;
import org.springframework.data.repository.query.Param;

@Repository
public interface IAttendanceRepository extends JpaRepository<Attendance, Integer> {

	Attendance findByEmployeeId(String employeeId);

	Attendance findByEmployeeIdAndDate(String employeeId, LocalDate currentDate);

	List<Attendance> findAllByEmployeeId(String employeeId);

//SELECT e.employee_id, e.name, IFNULL(a.applied_hrs_for_billing, 0) AS applied_hrs_for_billing
//
//FROM employee e
//LEFT JOIN (
//    SELECT employee_id, SUM(applied_hrs_for_billing) AS applied_hrs_for_billing
//    FROM attendance a
//    WHERE DATE_FORMAT(date, '%Y-%m') = '2024-03'
//    GROUP BY employee_id
//) a ON e.employee_id = a.employee_id
//WHERE e.manager = 'EIS00001';
	
	
//	@Query(value = "SELECT e.employee_id, e.name, IFNULL(a.applied_hrs_for_billing, 0) AS applied_hrs_for_billing"
//
//			+ "FROM employee e" + "LEFT JOIN ("
//			+ "    SELECT employee_id, SUM(applied_hrs_for_billing) AS applied_hrs_for_billing"
//			+ "    FROM attendance a" + "    WHERE DATE_FORMAT(date, '%Y-%m') = :month" + "    GROUP BY employee_id"
//			+ ") a ON e.employee_id = a.employee_id" + "WHERE e.manager = :managerId", nativeQuery = true)
//	List<Object[]> findAttendanceByManagerAndMonth(@Param("managerId") String managerId, @Param("month") String month);

	List<Attendance> findAllByDate(LocalDate date);

//	Attendance findByDate(LocalDate date);

}
