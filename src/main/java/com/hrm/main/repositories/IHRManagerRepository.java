package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.HRManager;

@Repository
public interface IHRManagerRepository extends JpaRepository<HRManager, Integer> {

	HRManager findByCandidateId(long candidateId);
}
