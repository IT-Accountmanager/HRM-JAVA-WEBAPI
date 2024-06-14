package com.hrm.services;

import com.hrm.payloads.WorkBookSubmission;

public interface IWorkBookService {

	String checkWorkBook(WorkBookSubmission workBookSubmission);

	WorkBookSubmission getWorkBookCheck(Long candidateId);

}
