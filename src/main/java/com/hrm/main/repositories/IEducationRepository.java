package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Education;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Integer> {

	Education findByCandidateId(long candidateId);

	List<Education> findAllByCandidateId(long candidateId);

}
