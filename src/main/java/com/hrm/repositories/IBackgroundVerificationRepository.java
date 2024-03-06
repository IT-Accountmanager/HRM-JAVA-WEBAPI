package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.models.BackgroundVerification;

public interface IBackgroundVerificationRepository extends JpaRepository<BackgroundVerification, Long> {

	BackgroundVerification findByCandidateId(long candidateId);

}
