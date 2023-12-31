package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.DemoEmployee;

@Repository
public interface DemoEmployeeRepository extends JpaRepository<DemoEmployee, Integer>{

}
