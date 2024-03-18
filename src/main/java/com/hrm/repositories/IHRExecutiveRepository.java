package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.HRExecutive;

@Repository
public interface IHRExecutiveRepository extends JpaRepository<HRExecutive, Integer> {

	HRExecutive findByCandidateId(long candidateId);

}