package com.hrm.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrm.models.WorkBook;

@Repository
public interface IWorkBookRepository extends JpaRepository<WorkBook, Serializable> {

	WorkBook findByCandidateId(Long candidateId);

}
