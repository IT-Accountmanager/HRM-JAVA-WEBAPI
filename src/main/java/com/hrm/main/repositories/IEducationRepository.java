package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Education;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Integer> {

}