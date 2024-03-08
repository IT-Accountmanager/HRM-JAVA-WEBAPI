package com.hrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.Departments.Department;
import com.hrm.models.Work;
import com.hrm.payloads.WorkDto;
import com.hrm.payloads.WorkInfoDto;
import com.hrm.payloads.WorkStatusResponse;

@Repository
public interface IWorkRepository extends JpaRepository<Work, Integer> {

	Work findByCandidateId(long candidateId);

	List<Work> findAllWorkByCandidateId(long candidateId);


}
