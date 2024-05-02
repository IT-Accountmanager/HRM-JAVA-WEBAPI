package com.hrm.servicesImpls;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.hrm.models.Holiday;
import com.hrm.repositories.IHolidayRepository;
import com.hrm.services.IHolidayService;

@Service
public class HolidayServiceImpl implements IHolidayService {

	public static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

	@Autowired
	IHolidayRepository holidayRepository;

	@Override
	public String add(ArrayNode holidays) {

		logger.info("Holidays Method Started");

		if (holidays == null || holidays.isEmpty()) {
			logger.warn("No holidays provided to add.");
			return "No holidays provided to add.";
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			Set<String> existingHolidays = new HashSet<>();

			for (JsonNode node : holidays) {

				try {

					Holiday holiday = mapper.treeToValue(node, Holiday.class);

					String holidayKey = holiday.getHolidayName() + "_" + holiday.getDate();
					if (existingHolidays.contains(holidayKey)) {
						logger.debug("Holiday " + holiday.getHolidayName() + " on " + holiday.getDate()
								+ " is already added.");
						continue;
					}

					Holiday existingHoliday = holidayRepository.findByHolidayNameAndDate(holiday.getHolidayName(),
							holiday.getDate());
					if (existingHoliday != null) {
						logger.debug("Holiday " + holiday.getHolidayName() + " on " + holiday.getDate()
								+ " is already added.");
						continue;
					}

					this.holidayRepository.save(holiday);
					existingHolidays.add(holidayKey);

					logger.debug("Holiday " + holiday + " is added.");
				} catch (Exception e) {
					logger.error("Error Occure in Add Holiday Method ::", e);
				}
			}
			logger.info("Holidays Successfully added");
			return "Holidays Successfully added";

		} catch (Exception e) {
			logger.error("An error occurred while adding holidays", e);
			return "Failed to add holidays: " + e.getMessage();
		}
	}

	@Override
	public List<Holiday> getListOfHolidays() {
		return holidayRepository.findAll();
	}
}
