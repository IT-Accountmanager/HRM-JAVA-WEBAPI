package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.Policies;

@Repository
public interface IPoliciesRepository extends JpaRepository<Policies, Integer> {

}
