package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.Agreement;

public interface IAgreementRepository extends JpaRepository<Agreement, Integer>{

}
