package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.BankDetails;

@Repository
public interface IBankDetailsRepository extends JpaRepository<BankDetails, Integer> {

}
