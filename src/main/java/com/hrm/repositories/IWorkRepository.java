package com.hrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.Work;

@Repository
public interface IWorkRepository extends JpaRepository<Work, Integer> {

	Work findByCandidateId(long candidateId);

	List<Work> findAllWorkByCandidateId(long candidateId);


}
