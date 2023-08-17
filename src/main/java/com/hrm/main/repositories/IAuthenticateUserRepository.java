/*
 * package com.hrm.main.repositories;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.data.jpa.repository.Query; import
 * org.springframework.stereotype.Repository; import
 * com.hrm.main.models.AuthenticateUserEntity;
 * 
 * @Repository public interface IAuthenticateUserRepository extends
 * JpaRepository<AuthenticateUserEntity, String> {
 * 
 * @Query("SELECT u FROM RegisterUser u WHERE u.emailId = ?1 AND u.password = ?2"
 * ) AuthenticateUserEntity findByUserIdAndPassword(String emailId, String
 * password); }
 */