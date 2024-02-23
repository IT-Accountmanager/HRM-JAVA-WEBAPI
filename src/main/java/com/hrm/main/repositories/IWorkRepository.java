package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Work;
import com.hrm.main.models.Helper.EnumCollection.Departments.Department;
import com.hrm.main.models.Helper.EnumCollection.Designation;
import com.hrm.main.payloads.WorkDto;
import com.hrm.main.payloads.WorkInfoDto;
import com.hrm.main.payloads.WorkStatusResponse;

@Repository
public interface IWorkRepository extends JpaRepository<Work, Integer> {

	Work findByCandidateId(long candidateId);

	List<Work> findAllWorkByCandidateId(long candidateId);


}
