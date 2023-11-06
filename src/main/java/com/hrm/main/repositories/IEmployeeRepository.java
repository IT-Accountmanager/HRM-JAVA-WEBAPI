package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
