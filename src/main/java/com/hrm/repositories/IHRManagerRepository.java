package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.HRManager;

@Repository
public interface IHRManagerRepository extends JpaRepository<HRManager, Integer> {

	HRManager findByCandidateId(long candidateId);
}
