package com.hrm.main.services;

import com.hrm.main.models.WorkHistory;

public interface IWorkHistoryService {

	String addWorkHistory(WorkHistory workHistory);

	String updateWorkHistory(WorkHistory workHistory, Integer id);

	WorkHistory getWorkHistory(Integer id);
}
