package com.hrm.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.Holiday;
import com.hrm.repositories.IHolidayRepository;
import com.hrm.services.IHolidayService;

@Service
public class HolidayServiceImpl implements IHolidayService {

	@Autowired
	IHolidayRepository holidayRepository;

	@Override
	public String add(List<Holiday> holiday) {

		return "holidays successfully added";
	}

}
