package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.SocialDetails;

@Repository
public interface ISocialDetailsRepository extends JpaRepository<SocialDetails, Integer> {

}
