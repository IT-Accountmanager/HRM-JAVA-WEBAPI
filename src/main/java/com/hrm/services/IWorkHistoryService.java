package com.hrm.services;

import com.hrm.payloads.WorkHistoryDto;

public interface IWorkHistoryService {

	String addWorkHistory(WorkHistoryDto workHistory);

	String updateWorkHistory(WorkHistoryDto workHistory, Integer id);

	WorkHistoryDto getWorkHistory(Integer id);
}
