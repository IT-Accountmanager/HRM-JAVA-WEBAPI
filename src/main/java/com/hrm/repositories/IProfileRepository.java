
package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrm.models.Profile;

public interface IProfileRepository extends JpaRepository<Profile, Integer> {

	/*
	 * @Query("UPDATE onboarding onb set onb.status = ?2 where onb.srNo = ?1")
	 * String profileStatusChange(Integer id, String status);
	 */

}
