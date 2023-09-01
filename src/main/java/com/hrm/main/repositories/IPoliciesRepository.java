package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Policies;

@Repository
public interface IPoliciesRepository extends JpaRepository<Policies, Integer> {

}
