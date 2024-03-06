package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.Regularization;
@Repository
public interface IRegularizationRepository extends JpaRepository<Regularization, Integer> {

}
