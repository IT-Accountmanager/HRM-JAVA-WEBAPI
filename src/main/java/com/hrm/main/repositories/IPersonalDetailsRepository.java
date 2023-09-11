package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.PersonalDetails;

import jakarta.mail.FetchProfile.Item;

@Repository
public interface IPersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {

	
}
