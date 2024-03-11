
package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.NewJobs;

@Repository
public interface INewJobsRepository extends JpaRepository<NewJobs, Integer> {

}
