package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.ResignationInfo;

@Repository
public interface IResignationInfoRepository extends JpaRepository<ResignationInfo, Integer> {

}