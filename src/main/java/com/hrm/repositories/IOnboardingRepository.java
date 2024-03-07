package com.hrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrm.helper.EnumCollection;
import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.models.Onboarding;
import com.hrm.models.RegisterUserEntity;

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

	/*
	 * @Query("SELECT u FROM RegisterUser u WHERE u.emailId = ?1 AND u.password = ?2"
	 * ) RegisterUserEntity findByUserIdAndPassword(String emailId, String
	 * password);
	 */

	// @Query(value = "SELECT * FROM onboarding WHERE status = ?1", nativeQuery =
	// true)
	List<Onboarding> findAllByCandidatesStatus(CandidatesStatus status);

	Onboarding findByCandidateId(long candidateId);

	List<Onboarding> findAllByCandidatesStatusIn(List<EnumCollection.CandidatesStatus> candidatesStatus);

	boolean existsByCandidateId(long candidateId);

	Onboarding findByEmailIdOrContactNumber(String emailId, long contactNumber);
	
	

}
