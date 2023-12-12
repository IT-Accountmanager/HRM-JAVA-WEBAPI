package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.Agreement;

import jakarta.transaction.Transactional;

public interface IAgreementRepository extends JpaRepository<Agreement, Integer> {

	Agreement findByCandidateId(long candidateId);
	// @Transactional
	// void deleteByEmployeeId(String employeeId);

	Boolean existsByCandidateId(long candidateId);

	// Agreement findByEmployeeId(String employeeId);

}
