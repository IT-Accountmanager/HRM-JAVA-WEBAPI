package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Work;
import com.hrm.main.payloads.WorkStatusResponse;

@Repository
public interface IWorkRepository extends JpaRepository<Work, Integer> {

	Work findByCandidateId(long candidateId);

	List<Work> findAllWorkByCandidateId(long candidateId);

}
