package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Onboarding;

@Repository
public interface IOnboardingRepository extends JpaRepository<Onboarding, Integer> {

	/*
	 * @Query("SELECT next_val FROM onboarding")
	 */
	// @Query(value = "SELECT nextval('Onboarding_seq')", nativeQuery = true)
	/*
	 * @Query(value = "SELECT nextval('public.Onboarding_seq')", nativeQuery = true)
	 * 
	 * Long findNextSrNo();
	 */

	/*
	 * @Query("UPDATE onboarding onb set onb.status = ?2 where onb.srNo = ?1")
	 * String profileStatusChange(Integer id, String status);
	 */

}
