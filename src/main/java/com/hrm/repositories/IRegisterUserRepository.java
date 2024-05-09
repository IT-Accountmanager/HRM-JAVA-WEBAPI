package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.models.RegisterUserEntity;

@Repository
public interface IRegisterUserRepository extends JpaRepository<RegisterUserEntity, Integer> {

	RegisterUserEntity findByEmailIdAndPassword(String emailId, String password);

	String findUserNameByEmailIdAndPassword(String emailId, String password);

//	@Query("SELECT u FROM RegisterUser u WHERE u.emailId = ?1 AND u.password =?2")
//	RegisterUserEntity findByEmailIdAndPassword(String emailId, String password);

//	@Query(value = "SELECT * FROM register_user u WHERE u.email_id = :emailId AND u.password = :password", nativeQuery = true)
//
//	Object findByEmailIdAndPassword(@Param("emailId") String emailId, @Param("password") String password);

//	@Query(value = "SELECT * FROM register_user WHERE email_id = ?1 AND password = ?2", nativeQuery = true)
//	RegisterUserEntity findByEmailIdAndPassword(String emailId, String password);

}
