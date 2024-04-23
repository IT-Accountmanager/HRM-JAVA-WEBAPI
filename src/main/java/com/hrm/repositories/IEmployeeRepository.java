package com.hrm.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Departments.Department;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.models.Employee;
import com.hrm.models.Onboarding;
import com.hrm.payloads.DirectReportsDto;
import com.hrm.payloads.SubDepartmentAndName;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	boolean existsByCandidateId(long candidateId);

	List<Employee> findAllByDepartment(Departments department);

	Employee findByCandidateId(long candidateId);

	boolean existsByEmailId(String emailId);

	boolean existsByContactNumber(long contactNumber);

	List<Employee> findByNameContainingIgnoreCase(String searchTerm);

	Employee findByEmployeeId(String employeeId);

	boolean existsByEmailIdOrContactNumber(String emailId, long contactNumber);

	/*
	 * List<Employee> findAllBySubDepartment(Sub_Department subDepartment);
	 */

	// List<Employee> findAllByDepartmentAndSubDepartment(Departments department,
	// Sub_Department subDepartment);

	/*
	 * List<Employee> findByDepartmentAndSubDepartmentAndDesignation(Departments
	 * department, Sub_Department subDepartment, String designation);
	 */

	/*
	 * List<Employee> findByDepartmentAndSubDepartmentAndDesignationNot(Departments
	 * department, Sub_Department subDepartment, String designation);
	 */

	List<Employee> findByEmployeeStatus(EmployeeStatus employeeStatus);

	List<Employee> findAllByManager(String manager);

	boolean existsByEmployeeId(String employeeId);

	// List<Employee> findSummary(String employeeId);

	/*
	 * @Query(value =
	 * "SELECT e.employee_id, e.name, e.employee_status, e.employee_category, e.contact_number, e.email_id, e.date_of_joining, e.department, e.sub_department, e.manager, e.designation, e.category_control, e.total_experience, e.joined_ctc, e.current_ctc, e.service_commitment, e.number_of_working_days, e.next_apprisal_quater, pd.date_of_birth, pd.blood_group, pd.fathers_name, dd.adhar_card_no, dd.pan_card_no, e.uan_number, bd.account_no, e.resignation_date, e.last_working_day\r\n"
	 * + "FROM Employee e \r\n" +
	 * "INNER JOIN personal p ON e.candidate_id = p.candidate_id\r\n" +
	 * "INNER JOIN personal_details pd ON p.pdid = pd.pdid\r\n" +
	 * "INNER JOIN document_details dd ON p.doc_id = dd.doc_id\r\n" +
	 * "INNER JOIN bank_details bd ON p.bank_det_id = bd.id", nativeQuery = true)
	 */

	@Query(value = "SELECT e.employee_id, e.name AS employee_name, e.employee_status, e.employee_category, e.contact_number, e.email_id, e.date_of_joining, e.department, e.sub_department, m.name AS manager_name, e.designation, e.category_control, e.total_experience, e.joined_ctc, e.current_ctc, e.service_commitment, e.number_of_working_days, e.next_apprisal_quater, pd.date_of_birth, pd.blood_group, pd.fathers_name, dd.adhar_card_no, dd.pan_card_no, e.uan_number, bd.account_no, e.resignation_date, e.last_working_day, ed.qualification, ed.end_date, ed.stream,e.candidate_id, e.manager,e.number_of_working_days\r\n"
			+ "FROM Employee e\r\n" + "INNER JOIN\r\n" + "    personal p ON e.candidate_id = p.candidate_id\r\n"
			+ "INNER JOIN\r\n" + "    personal_details pd ON p.pdid = pd.pdid\r\n" + "INNER JOIN\r\n"
			+ "    document_details dd ON p.doc_id = dd.doc_id\r\n" + "INNER JOIN\r\n"
			+ "    bank_details bd ON p.bank_det_id = bd.id\r\n" + "INNER JOIN\r\n" + "    (SELECT\r\n"
			+ "         candidate_id,\r\n" + "         MAX(end_date) AS max_end_date\r\n" + "     FROM\r\n"
			+ "         education\r\n" + "     GROUP BY\r\n" + "         candidate_id\r\n"
			+ "    ) max_edu ON p.candidate_id = max_edu.candidate_id\r\n" + "INNER JOIN\r\n"
			+ "    education ed ON p.candidate_id = ed.candidate_id AND ed.end_date = max_edu.max_end_date LEFT JOIN\r\n"
			+ "    Employee m ON e.manager = m.employee_id;", nativeQuery = true)
	List<Object[]> findSummaryData();

	Employee findSubDepartmentAndNameByEmployeeId(String employeeId);

	@Query(value = "SELECT e.name, e.sub_department, e.designation, e.manager_from, e.manager_to FROM employee e WHERE e.manager =:managerId", nativeQuery = true)
	List<Object[]> findByManagerId(@Param("managerId") String managerId);

	@Query(value = "SELECT m.name AS manager_name, e.manager_type, e.department, e.designation, e.manager_from, e.manager_to FROM employee e JOIN employee m ON e.manager = m.employee_id WHERE e.employee_id = :employeeId", nativeQuery = true)
	List<Object[]> findReportingManagerByEmployeeId(String employeeId);

}
