package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.BasicInfo;

public interface IBasicInfoRepository extends JpaRepository<BasicInfo, Integer> {

}
