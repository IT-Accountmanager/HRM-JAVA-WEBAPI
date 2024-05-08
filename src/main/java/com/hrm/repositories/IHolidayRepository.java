package com.hrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hrm.models.Holiday;

@Repository
public interface IHolidayRepository extends JpaRepository<Holiday, Integer> {
	boolean existsByDate(String date);

	Holiday findByHolidayNameAndDate(String holidayName, String date);

	@Query(value = "SELECT h.date, h.holiday_name FROM holiday h", nativeQuery = true)
	List<Object[]> getAllHolidays();

	// boolean isHoliday(LocalDate currentDate);

}
