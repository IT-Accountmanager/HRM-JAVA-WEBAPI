package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.JVM;

@Repository
public interface IJVMRepository extends JpaRepository<JVM, Integer> {

}
