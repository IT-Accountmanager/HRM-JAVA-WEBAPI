package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
