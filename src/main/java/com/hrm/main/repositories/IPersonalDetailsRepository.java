package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.PersonalDetails;

@Repository
public interface IPersonalDetailsRepository extends JpaRepository<PersonalDetails, Integer> {

}
