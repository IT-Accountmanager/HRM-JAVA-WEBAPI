package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Family;

@Repository
public interface IFamilyRepository extends JpaRepository<Family, Integer> {

}
