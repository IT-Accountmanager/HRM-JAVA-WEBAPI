package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.WorkInfo;

public interface IWorkInfoRepository extends JpaRepository<WorkInfo, Integer> {

}
