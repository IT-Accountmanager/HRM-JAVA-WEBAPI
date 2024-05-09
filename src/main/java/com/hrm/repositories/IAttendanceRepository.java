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

	@Query(value = "SELECT  " + "    e.employee_id,  e.name,  e.sub_department, "
			+ "    MONTH(a.date) AS attendance_month, " + "    COUNT(*) AS present_days, "
			+ "    SUM(a.approved_hrs_for_billing) AS total_approved_hours " + "FROM  " + "    Attendance a "
			+ "LEFT JOIN  " + "    employee e ON a.employee_id = e.employee_id " + "WHERE  "
			+ "    e.manager = :managerId " + "     AND YEAR(a.date) = :year   " + "    AND MONTH(a.date) = :month    "
			+ "GROUP BY  " + "    e.employee_id, " + "    e.name, " + "    e.sub_department, " + "    MONTH(a.date); "
			+ "", nativeQuery = true)
	List<Object[]> findByManagerIdAndMonth(@Param("managerId") String managerId, @Param("year") int year,
			@Param("month") int month);

	@Query(value = "select a.employee_id, a.month , a.date , a.in_time , a.out_time , a.work_hrs , a.attendance_status , m.name AS manager , a.project_id , a.applied_hrs_for_billing , a.approved_hrs_for_billing , a.remarks  "
			+ "from attendance a  " + "join employee e  " + "on a.employee_id = e.employee_id  "
			+ "left join employee m  " + "on e.manager = m.employee_id " + "where a.employee_id = :employeeId  "
			+ "AND MONTH(a.date) = :month " + "AND YEAR(a.date) = :year ; ", nativeQuery = true)
	List<Object[]> findAllAttendance(@Param("employeeId") String employeeId, @Param("month") Integer month,
			@Param("year") Integer year);

	@Query(value = " SELECT m.name " + "FROM employee e " + "JOIN employee m ON e.manager = m.employee_id "
			+ "WHERE e.employee_id = :employee_id ;", nativeQuery = true)
	Object[] findManager(@Param("employee_id") String employeeId);

	@Query(value = "SELECT  "
			+ "  main.employee_id,  "
			+ "  main.name,  "
			+ "  main.manager,  "
			+ "  main.total_days,  "
			+ "  main.present_days,  "
			+ "  sub.leaves,  "
			+ "  main.approved_billable_hrs  "
			+ "FROM  "
			+ "  ( "
			+ "    SELECT  "
			+ "      a.employee_id,  "
			+ "      e.name,  "
			+ "      m.name AS manager,  "
			+ "      DAY( "
			+ "        LAST_DAY(a.date) "
			+ "      ) as total_days,  "
			+ "      COUNT(*) - COUNT( "
			+ "        CASE WHEN DAYOFWEEK(a.date) IN (1, 7) THEN 1 END "
			+ "      ) AS present_days,  "
			+ "      MAX(a.approved_hrs_for_billing) as approved_billable_hrs  "
			+ "    FROM  "
			+ "      attendance a  "
			+ "      LEFT JOIN employee e ON a.employee_id = e.employee_id  "
			+ "      LEFT JOIN employee m ON e.manager = m.employee_id  "
			+ "    WHERE  "
			+ "      MONTH(a.date) = :month  "
			+ "      AND YEAR(a.date) = :year  "
			+ "      AND DAYOFWEEK(a.date) NOT IN (1, 7)  "
			+ "      AND a.attendance_status = 'P'  "
			+ "    GROUP BY  "
			+ "      a.employee_id,  "
			+ "      e.name,  "
			+ "      m.name,  "
			+ "      DAY( "
			+ "        LAST_DAY(a.date) "
			+ "      ) "
			+ "  ) AS main  "
			+ "  LEFT JOIN ( "
			+ "    SELECT  "
			+ "      employee_id,  "
			+ "      SUM(approved_days_for_leave) as leaves  "
			+ "    FROM  "
			+ "      leave_management_table  "
			+ "    GROUP BY  "
			+ "      employee_id "
			+ "  ) AS sub ON main.employee_id = sub.employee_id  "
			+ "LIMIT  "
			+ "  0, 1000; "
			+ "", nativeQuery = true)
	List<Object[]> getSummary(@Param("month") Integer month,@Param("year") Integer year);

}