package com.hrm.repositories;

import java.util.List;

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

	@Query(value = "SELECT  "
			+ "    lmt.leave_type, "
			+ "    lmt.leave_start_date, "
			+ "    lmt.leave_end_date, "
			+ "    lmt.applied_days_for_leave, "
			+ "    lmt.leave_reason, "
			+ "    e.name,  "
			+ "    m.name AS manager,  "
			+ "    e.department,  "
			+ "    e.designation, "
			+ "    pd.profile_photo "
			+ "FROM  "
			+ "    leave_management_table lmt  "
			+ "    LEFT JOIN employee e ON lmt.employee_id = e.employee_id "
			+ "    LEFT JOIN employee m ON e.manager = m.employee_id "
			+ "    LEFT JOIN personal_details pd ON e.email_id = pd.personal_mail_id "
			+ "WHERE  "
			+ "    lmt.id = :id "
			+ "GROUP BY  "
			+ "    lmt.leave_type, "
			+ "    lmt.leave_start_date, "
			+ "    lmt.leave_end_date, "
			+ "    lmt.applied_days_for_leave, "
			+ "    lmt.leave_reason, "
			+ "    e.name,  "
			+ "    m.name,  "
			+ "    e.department,  "
			+ "    e.designation, "
			+ "    pd.profile_photo "
			+ "", nativeQuery = true)
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
