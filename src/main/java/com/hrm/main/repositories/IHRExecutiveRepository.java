package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrm.main.models.HRExecutive;


@Repository
public interface IHRExecutiveRepository extends JpaRepository<HRExecutive, Integer> {

	HRExecutive findByCandidateId(String candidateId);

	/*
	 * @Query("SELECT o.candidate_name FROM `actus-web-api`.onboarding o left join personal p on o.candidate_id =p.candidate_id \r\n"
	 * + "left join family f on o.candidate_id = f.candidate_id \r\n" +
	 * "left join education e on o.candidate_id = e.candidate_id\r\n" +
	 * "left join work w on o.candidate_id = w.candidate_id\r\n" +
	 * "where p.status=1 and f.status =1 and e.status=1 and w.status=1 ")
	 */

	/*
	 * @Procedure(value = "getAllOnboardingInHrExecutive")
	 * 
	 * @Transactional
	 */

	/*
	 * @Query("SELECT o.job_title, o.candidate_id, o.candidate_name, o.contact_number, o.email_id, o.bond_period, o.bond_break_amount, o.ctc FROM `actus-web-api`.onboarding o left join personal p on o.candidate_id =p.candidate_id left join family f on o.candidate_id = f.candidate_id left join education e on o.candidate_id = e.candidate_id left join work w on o.candidate_id = w.candidate_id where p.status=1 and f.status =1 and e.status=1 and w.status=1 "
	 * ) List<Onboarding> getAllOnboarding();
	 */
}
