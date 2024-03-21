package com.hrm.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.models.Holiday;

@Repository
public interface IHolidayRepository extends JpaRepository<Holiday, Integer> {
	boolean existsByDate(LocalDate date);

	// boolean isHoliday(LocalDate currentDate);

}
