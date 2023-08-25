package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.WorkHistory;

@Repository
public interface IWorkHistoryRepository extends JpaRepository<WorkHistory, Integer> {

}
