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

	@Query(value = "SELECT lmt.*, emp.name, emp.manager, emp.department, emp.designation, pd.profile_photo "
			+ "FROM leave_management_table lmt " + "LEFT JOIN employee emp ON emp.employee_id = lmt.employee_id "
			+ "LEFT JOIN personal_details pd ON emp.email_id = pd.personal_mail_id "
			+ "WHERE lmt.id = :id", nativeQuery = true)
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

//	 @Query("SELECT e.designation, e.department FROM Employee e WHERE e.employeeId = :employeeId")
//	    Object[] findDesignationAndDepartmentByEmployeeId(@Param("employeeId") Long employeeId);

}
