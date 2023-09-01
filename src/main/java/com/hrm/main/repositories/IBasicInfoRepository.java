package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.BasicInfo;

@Repository
public interface IBasicInfoRepository extends JpaRepository<BasicInfo, Integer> {

}
