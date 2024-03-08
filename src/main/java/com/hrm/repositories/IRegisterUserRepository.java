package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrm.models.RegisterUserEntity;

@Repository
public interface IRegisterUserRepository extends JpaRepository<RegisterUserEntity, Integer> {

	@Query("SELECT u FROM RegisterUser u WHERE u.emailId = ?1 AND u.password = ?2")
	RegisterUserEntity findByUserIdAndPassword(String emailId, String password);
}
