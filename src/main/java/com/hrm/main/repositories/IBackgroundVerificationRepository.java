package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.main.models.BackgroundVerification;

public interface IBackgroundVerificationRepository extends JpaRepository<BackgroundVerification, Long> {

	BackgroundVerification findByCandidateId(long candidateId);

}
