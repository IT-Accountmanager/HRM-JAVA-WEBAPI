package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Personal;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Integer> {

}
