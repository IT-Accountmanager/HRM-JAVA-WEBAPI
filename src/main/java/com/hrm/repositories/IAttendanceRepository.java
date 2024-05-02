package com.hrm.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
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

	/*
	 * @Query(value =
	 * "SELECT e.employee_id, e.name, IFNULL(a.applied_hrs_for_billing, 0) AS applied_hrs_for_billing"
	 * + "FROM employee e " + "LEFT JOIN (" +
	 * "    SELECT employee_id, SUM(applied_hrs_for_billing) AS applied_hrs_for_billing "
	 * + "    FROM attendance a " + "    WHERE DATE_FORMAT(date, '%Y-%m') = :month "
	 * + "    GROUP BY employee_id " + ") a ON e.employee_id = a.employee_id " +
	 * "WHERE e.manager = :managerId", nativeQuery = true) List<Object[]>
	 * findAttendanceByManagerAndMonth(@Param("managerId") String
	 * managerId, @Param("month") String month);
	 */

	/*
	 * @Query(value =
	 * "SELECT e.employee_id, e.name, IFNULL(a.applied_hrs_for_billing, 0) AS applied_hrs_for_billing "
	 * + "FROM employee e " + "LEFT JOIN (" +
	 * "    SELECT employee_id, SUM(applied_hrs_for_billing) AS applied_hrs_for_billing "
	 * + "    FROM attendance a " + "    WHERE DATE_FORMAT(date, '%Y-%m') = :month "
	 * + "    GROUP BY employee_id " + ") a ON e.employee_id = a.employee_id " +
	 * "WHERE e.manager = :managerId", nativeQuery = true)
	 */

//Kartik	
//	@Query(value = "SELECT e.employee_id, e.name, e.department, "
//			+ "IFNULL(a.applied_hrs_for_billing, 0) AS applied_hrs_for_billing, "
//			+ "IFNULL(a.present_days, 0) AS present_days " + "FROM employee e " + "LEFT JOIN ( "
//			+ "    SELECT employee_id, " + "           SUM(applied_hrs_for_billing) AS applied_hrs_for_billing, "
//			+ "           COUNT(*) AS present_days " + "    FROM attendance a "
//			+ "    WHERE DATE_FORMAT(date, '%Y-%m') = :month " + "    GROUP BY employee_id "
//			+ ") a ON e.employee_id = a.employee_id " + "WHERE e.manager = :managerId", nativeQuery = true)

//	@Query(value = "SELECT e.employee_id, e.name, e.department, " +
//	        "IFNULL(a.applied_hrs_for_billing, 0) AS applied_hrs_for_billing, " +
//	        "IFNULL(a.present_days, 0) AS present_days " +
//	        "FROM employee e " +
//	        "LEFT JOIN ( " +
//	        "    SELECT employee_id, " +
//	        "           SUM(applied_hrs_for_billing) AS applied_hrs_for_billing, " +
//	        "           COUNT(*) AS present_days " +
//	        "    FROM attendance a " +
//	        "    WHERE DATE_FORMAT(date, '%Y-%m') = :month " +
//	        "    GROUP BY employee_id " +
//	        ") a ON e.employee_id = a.employee_id " +
//	        "WHERE e.manager = :managerId", nativeQuery = true)

//Ramchandra
	@Query(value = "SELECT e.employee_id, e.name, e.department, \n"
			+ "	        IFNULL(a.approved_hrs_for_billing, 0) AS approved_hrs_for_billing, \n"
			+ "	        IFNULL(a.present_days, 0) AS present_days \n" + "	        FROM employee e \n"
			+ "	        LEFT JOIN ( \n" + "	            SELECT employee_id, \n"
			+ "	                   SUM(approved_hrs_for_billing) AS approved_hrs_for_billing, \n"
			+ "	                   COUNT(*) AS present_days \n" + "	            FROM attendance a \n"
			+ "	            WHERE DATE_FORMAT(date, '%Y-%m') = :month" + "	            GROUP BY employee_id \n"
			+ "	        ) a ON e.employee_id = a.employee_id \n"
			+ "	        WHERE e.manager = :managerId", nativeQuery = true)

	List<Object[]> findAttendanceByManagerAndMonth(@Param("managerId") String managerId, @Param("month") String month);

	@Query(value = "SELECT e.employee_id, e.name, e.department, \n"
			+ "	        IFNULL(a.approved_hrs_for_billing, 0) AS approved_hrs_for_billing, \n"
			+ "	        IFNULL(a.present_days, 0) AS present_days \n" + "	        FROM employee e \n"
			+ "	        LEFT JOIN ( \n" + "	            SELECT employee_id, \n"
			+ "	                   SUM(approved_hrs_for_billing) AS approved_hrs_for_billing, \n"
			+ "	                   COUNT(*) AS present_days \n" + "	            GROUP BY employee_id \n"
			+ "	        ) a ON e.employee_id = a.employee_id \n"
			+ "	        WHERE e.manager = :managerId", nativeQuery = true)

	List<Object[]> findAttendanceByManager(@Param("managerId") String managerId);

	 List<Attendance> findAllByDate(LocalDate date);

//	@Query(value = "SELECT a.id , e.employee_id, e.name, e.sub_department, MONTH(a.date) AS attendance_month, "
//			+ "SUM(CASE WHEN MONTH(a.date) = :month THEN 1 ELSE 0 END) AS present_days, "
//			+ "SUM(a.approved_hrs_for_billing) AS total_approved_hours " + "FROM attendance a "
//			+ "LEFT JOIN employee e ON a.employee_id = e.employee_id "
//			+ "WHERE e.manager = :managerId AND YEAR(a.date) = :year AND MONTH(a.date) = :month "
//			+ "GROUP BY a.id , e.employee_id, e.name, e.sub_department, MONTH(a.date)", nativeQuery = true)

	@Query(value = "SELECT  "
			+ "    e.employee_id, "
			+ "    e.name, "
			+ "    e.sub_department, "
			+ "    MONTH(a.date) AS attendance_month, "
			+ "    COUNT(*) AS present_days, "
			+ "    SUM(a.approved_hrs_for_billing) AS total_approved_hours "
			+ "FROM  "
			+ "    Attendance a "
			+ "LEFT JOIN  "
			+ "    employee e ON a.employee_id = e.employee_id "
			+ "WHERE  "
			+ "    e.manager = :managerId "
			+ "     AND YEAR(a.date) = :year   "
			+ "    AND MONTH(a.date) = :month    "
			+ "GROUP BY  "
			+ "    e.employee_id, "
			+ "    e.name, "
			+ "    e.sub_department, "
			+ "    MONTH(a.date); "
			+ "", nativeQuery = true)
	List<Object[]> findByManagerIdAndMonth(@Param("managerId") String managerId
			, @Param("year") int year,
			@Param("month") int month
	);

}