package com.hrm.main.services;

import com.hrm.main.payloads.WorkHistoryDto;

public interface IWorkHistoryService {

	String addWorkHistory(WorkHistoryDto workHistory);

	String updateWorkHistory(WorkHistoryDto workHistory, Integer id);

	WorkHistoryDto getWorkHistory(Integer id);
}
