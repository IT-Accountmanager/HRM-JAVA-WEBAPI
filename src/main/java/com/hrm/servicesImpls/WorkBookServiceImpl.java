package com.hrm.servicesImpls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.WorkBook;
import com.hrm.payloads.WorkBookSubmission;
import com.hrm.repositories.IWorkBookRepository;
import com.hrm.services.IWorkBookService;

@Service
public class WorkBookServiceImpl implements IWorkBookService {
	@Autowired
	IWorkBookRepository workBookRepository;

	private static final Logger logger = LoggerFactory.getLogger(WorkBookServiceImpl.class);

	@Override
	public String checkWorkBook(WorkBookSubmission workBookSubmission) {
		logger.info("Inside Checking Work Book ");
		try {
			WorkBook workBook = this.workBookRepository.findByCandidateId(workBookSubmission.getCandidateId());
			if (workBook == null) {
				workBook = new WorkBook();
			}
			BeanUtils.copyProperties(workBookSubmission, workBook);
			this.workBookRepository.save(workBook);
//			BeanUtils.copyProperties(workBook, workBookSubmission);
		} catch (Exception e) {
			logger.error("Checking Work Book fails ! \n Reason : {}", e.getCause());
			return "Failed";
		}
		return "Submitted";
	}

	@Override
	public WorkBookSubmission getWorkBookCheck(Long candidateId) {
		logger.info("Inside getWorkBookCheck(Long candidateId) with candidateId = {}", candidateId);
		WorkBookSubmission workBookSubmission = new WorkBookSubmission();

		try {
			Optional<WorkBook> optional = Optional.ofNullable(this.workBookRepository.findByCandidateId(candidateId));
			optional.ifPresentOrElse(workBook -> {
				BeanUtils.copyProperties(workBook, workBookSubmission);
			}, () -> {
				logger.warn("No WorkBook found for candidateId = {}", candidateId);
			});
		} catch (Exception e) {
			logger.error("Exception occurred while getting WorkBook for candidateId = {}", candidateId, e);
		}
		return workBookSubmission;
	}

}
