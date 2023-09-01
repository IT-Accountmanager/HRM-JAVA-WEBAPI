package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrm.main.models.NewJobs;
@Repository
public interface INewJobsRepository extends JpaRepository<NewJobs, Integer> {

}
