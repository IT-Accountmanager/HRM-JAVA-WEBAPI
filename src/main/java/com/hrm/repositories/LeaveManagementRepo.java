package com.hrm.repositories;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.models.LeaveManagementTable;

@Repository
public interface LeaveManagementRepo extends JpaRepository<LeaveManagementTable, Integer> {

	LeaveManagementTable findByEmployeeId(String employeeId);

	/*
	 * List<LeaveManagementTable> findAllByManagerIdAndYear(String employeeId, int
	 * currentYear);
	 * 
	 * List<LeaveManagementTable> findAllByManagerIdAndLeaveStartDateYear(String
	 * employeeId, int currentYear, Month month);
	 * 
	 * List<LeaveManagementTable> findAllByManagerIdAndLeaveStartDate(String
	 * employeeId, int currentYear);
	 */

//	@Query("SELECT l FROM LeaveManagementTable l WHERE l.managerId = :managerId AND YEAR(l.leaveStartDate) = :yearAndMonth")
//	List<LeaveManagementTable> findByManagerIdAndYear(@Param("managerId") String managerId, @Param("yearAndMonth") LocalDate yearAndMonth);

//	@Query("SELECT l FROM LeaveManagementTable l WHERE l.managerId = :managerId AND YEAR(l.leaveStartDate) = YEAR(:yearAndMonth) AND MONTH(l.leaveStartDate) = MONTH(:yearAndMonth)")
//	List<LeaveManagementTable> findByManagerIdAndYear(@Param("managerId") String managerId, @Param("yearAndMonth") LocalDate yearAndMonth);

	// By sir

	/*
	 * @Query("SELECT l FROM LeaveManagementTable l WHERE l.managerId = :managerId AND DATE_FORMAT(l.leaveStartDate, '%Y-%c') = :monthInput"
	 * ) List<LeaveManagementTable> findByManagerIdAndYear(@Param("managerId")
	 * String managerId,
	 * 
	 * @Param("employeeId") String employeeId, @Param("monthInput") String
	 * monthInput);
	 */

	@Query(value = "SELECT DISTINCT pd.profile_photo,  emp.name, emp.department, emp.designation, lmt.leave_type, lmt.leave_start_date, lmt.leave_end_date, lmt.applied_days_for_leave, lmt.leave_reason, m.name AS manager_name  "
			+ "			FROM leave_management_table lmt INNER JOIN employee emp ON emp.employee_id = lmt.employee_id  "
			+ "			INNER JOIN personal_details pd ON emp.email_id = pd.personal_mail_id  "
			+ "            LEFT JOIN employee m ON emp.manager = m.employee_id "
			+ "			WHERE lmt.id = :id", nativeQuery = true)
	List<Object[]> findLeaveDetailsById(@Param("id") Integer id);

	@Query("SELECT l FROM LeaveManagementTable l WHERE l.managerId = :managerId AND DATE_FORMAT(l.leaveStartDate, '%Y-%c') = :monthInput")
	List<LeaveManagementTable> findByManagerIdAndYear(@Param("managerId") String managerId,
			@Param("monthInput") String monthInput);

	@Query("SELECT l FROM LeaveManagementTable l WHERE l.employeeId = :employeeId AND DATE_FORMAT(l.leaveStartDate, '%Y-%c') = :monthInput")
	List<LeaveManagementTable> findByEmployeeIdAndYear(@Param("employeeId") String employeeId,
			@Param("monthInput") String monthInput);

	@Query("SELECT l FROM LeaveManagementTable l WHERE (l.managerId = :managerId OR l.employeeId = :employeeId) AND DATE_FORMAT(l.leaveStartDate, '%Y-%c') = :monthInput")
	List<LeaveManagementTable> findByManagerIdAndYearOrEmployeeIdAndYear(@Param("managerId") String managerId,

			@Param("employeeId") String employeeId, @Param("monthInput") String monthInput);

	@Query(value = "SELECT  "
			+ "  l.employee_id,  "
			+ "  l.leave_start_date,  "
			+ "  l.leave_end_date  "
			+ "FROM  "
			+ "  leave_management_table l  "
			+ "WHERE  "
			+ "  employee_id = :employee_id  "
			+ "  and ( "
			+ "    month(l.leave_start_date) = :month  "
			+ "    and year(l.leave_start_date)= :year  "
			+ "    OR month(l.leave_end_date) = :month  "
			+ "    and year(l.leave_end_date)= :year "
			+ "  ); "
			+ "", nativeQuery = true)
	List<Object[]> findLeaves(@Param("employee_id") String employeeId,@Param("month") Integer month, Integer year);

//	 @Query("SELECT e.designation, e.department FROM Employee e WHERE e.employeeId = :employeeId")
//	    Object[] findDesignationAndDepartmentByEmployeeId(@Param("employeeId") Long employeeId);

}
