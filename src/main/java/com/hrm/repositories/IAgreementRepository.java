package com.hrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hrm.models.Agreement;

public interface IAgreementRepository extends JpaRepository<Agreement, Integer> {

	Agreement findByCandidateId(long candidateId);
	// @Transactional
	// void deleteByEmployeeId(String employeeId);

	Boolean existsByCandidateId(long candidateId);

	// Agreement findByEmployeeId(String employeeId);

	@Query(value = "SELECT  CURRENT_DATE() AS agreement_date, p.candidate_id,pd.first_name,pd.middle_name, pd.last_name ,pd.fathers_name,DATEDIFF(CURRENT_DATE(), pd.date_of_birth) / 365 AS age,o.service_commitment,o.service_break_amount, "
			+ "per.* ,pre.*, CURRENT_DATE() AS tenure_from, DATE_ADD(CURRENT_DATE(), INTERVAL (o.service_commitment * 365) DAY) AS tenure_to "
			+ " FROM  personal p  " + "join address_details ad on p.ad_id=ad.ad_id "
			+ "join permanent_address per on per.per_add_id=ad.ad_id "
			+ "join present_address pre on pre.pre_add_id= ad.ad_id " + "join personal_details pd on p.pdid = pd.pdid "
			+ "join onboarding o on p.candidate_id=o.candidate_id " + " where p.candidate_id = 1", nativeQuery = true)
	Object[] preAgreementDetails(@Param("candidateId") long candidateId);

}
