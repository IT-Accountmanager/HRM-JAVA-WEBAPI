package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);

	Boolean existsByUsername(String username);
}
