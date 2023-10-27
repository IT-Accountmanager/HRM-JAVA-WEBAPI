package com.hrm.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.main.models.Personal;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Helper.EnumCollection;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Integer> {

	// @Query(SELECT * FROM( SELECT p.candidate_id, pd.alternative_phone_no,
	// pd.pdid, pd.blood_group, pd.date_of_birth, pd.first_name, pd.gender,
	// pd.last_name, pd.marital_status, pd.middle_name, pd.official_mail_id,
	// pd.personal_mail_id, pd.phone_no, pd.profile_photo FROM personal_details pd
	// LEFT JOIN personal p ON pd.pdid = p.pdid) AS subquery WHERE
	// subquery.candidate_id = 101)

	/*
	 * @Query("SELECT\r\n" + "    subquery.candidate_id,\r\n" +
	 * "    subquery.alternative_phone_no,\r\n" + "    subquery.pdid,\r\n" +
	 * "    subquery.blood_group,\r\n" + "    subquery.date_of_birth,\r\n" +
	 * "    subquery.first_name,\r\n" + "    subquery.gender,\r\n" +
	 * "    subquery.last_name,\r\n" + "    subquery.marital_status,\r\n" +
	 * "    subquery.middle_name,\r\n" + "    subquery.official_mail_id,\r\n" +
	 * "    subquery.personal_mail_id,\r\n" + "    subquery.phone_no,\r\n" +
	 * "    subquery.profile_photo\r\n" + "FROM (\r\n" + "    SELECT\r\n" +
	 * "        p.candidate_id,\r\n" + "        pd.alternative_phone_no,\r\n" +
	 * "        pd.pdid,\r\n" + "        pd.blood_group,\r\n" +
	 * "        pd.date_of_birth,\r\n" + "        pd.first_name,\r\n" +
	 * "        pd.gender,\r\n" + "        pd.last_name,\r\n" +
	 * "        pd.marital_status,\r\n" + "        pd.middle_name,\r\n" +
	 * "        pd.official_mail_id,\r\n" + "        pd.personal_mail_id,\r\n" +
	 * "        pd.phone_no,\r\n" + "        pd.profile_photo\r\n" + "    FROM\r\n"
	 * + "        personal_details pd\r\n" + "    LEFT JOIN\r\n" +
	 * "        personal p\r\n" + "    ON\r\n" + "        pd.pdid = p.pdid\r\n" +
	 * ") AS subquery\r\n" + "WHERE\r\n" + "    subquery.candidate_id = 101;\r\n" +
	 * "")
	 */
	Personal findByCandidateId(long candidateId);

	Personal findPersonalSubmissionStatusByCandidateId(long candidateId);

	/*
	 * @Query("SELECT p.detailsSubmissionStatus FROM Personal p WHERE p.candidateId = :candidateId"
	 * ) EnumCollection.DetailsSubmissionStatus
	 * findSubmissionStatusByCandidateId(@Param("candidateId") String candidateId);
	 */
}
