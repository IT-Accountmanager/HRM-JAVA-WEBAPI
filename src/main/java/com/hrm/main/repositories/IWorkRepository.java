package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Work;
import com.hrm.main.payloads.WorkStatusResponse;

@Repository
public interface IWorkRepository extends JpaRepository<Work, Integer> {

	Work findByCandidateId(String candidateId);

}
