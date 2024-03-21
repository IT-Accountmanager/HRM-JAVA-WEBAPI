package com.hrm.servicesImpls;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.Holiday;
import com.hrm.repositories.IHolidayRepository;
import com.hrm.services.IHolidayService;

@Service
public class HolidayServiceImpl implements IHolidayService {

	public static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

	@Autowired
	IHolidayRepository holidayRepository;

	@Override
	public String add(List<Holiday> holiday) {

		try {

			List<Holiday> savedHolidays = holidayRepository.saveAll(holiday);
			if (savedHolidays != null && !savedHolidays.isEmpty()) {
				logger.info("Holidays Successfully added");
				return "Holidays Successfully added";
			} else {
				logger.warn("No holidays were saved");
				return "Failed to add holidays";
			}
		} catch (Exception e) {
			logger.error("An error occurred while adding holidays", e);
			return "Failed to add holidays: " + e.getMessage();
		}
	}
}
