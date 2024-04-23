package com.hrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.PersonalDetails;

@Repository
public interface IPersonalDetailsRepository extends JpaRepository<PersonalDetails, Integer> {

	static Optional<PersonalDetails> findById(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

}
