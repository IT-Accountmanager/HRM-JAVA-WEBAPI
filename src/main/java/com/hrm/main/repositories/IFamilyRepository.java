package com.hrm.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Family;

import jakarta.transaction.Transactional;

@Repository
public interface IFamilyRepository extends JpaRepository<Family, Integer> {

	@Transactional
	void deleteByFamilyId(int family_id);

	List<Family> findByCandidateId(long candidateId);

	List<Family> findAllByCandidateId(long candidateId);

	boolean existsByPhoneNumber(long phoneNumber);

}
