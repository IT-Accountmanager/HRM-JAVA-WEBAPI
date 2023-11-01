
package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrm.main.models.Profile;

public interface IProfileRepository extends JpaRepository<Profile, Integer> {

	/*
	 * @Query("UPDATE onboarding onb set onb.status = ?2 where onb.srNo = ?1")
	 * String profileStatusChange(Integer id, String status);
	 */

}
