package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Employee;
import com.hrm.main.models.Helper.EnumCollection.Departments;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	boolean existsByCandidateId(long candidateId);

	List<Employee> findAllByDepartment(Departments department);

	Employee findByCandidateId(long candidateId);

	boolean existsByEmailId(String emailId);

	boolean existsByContactNumber(long contactNumber);

	List<Employee> findByNameContainingIgnoreCase(String searchTerm);

	Employee findByEmployeeId(String employeeId);


}
