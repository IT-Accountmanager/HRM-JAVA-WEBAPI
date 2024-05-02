package com.hrm.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.Holiday;

@Repository
public interface IHolidayRepository extends JpaRepository<Holiday, Integer> {
	boolean existsByDate(String date);

	Holiday findByHolidayNameAndDate(String holidayName, String date);

	// boolean isHoliday(LocalDate currentDate);

}
