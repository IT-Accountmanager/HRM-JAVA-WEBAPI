package com.hrm.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.Attendance;
import com.hrm.models.NewJobs;
import com.hrm.repositories.INewJobsRepository;
import com.hrm.services.INewJobsService;

@Service
public class NewJobsImpl implements INewJobsService {
	@Autowired
	INewJobsRepository newJobsRepo;

	@Override
	public String addNewJobs(NewJobs newJobs) {
		try {

			NewJobs save = this.newJobsRepo.save(newJobs);
			if (save.getId() > 0) {
				return "New Jobs of Id no. " + save.getId() + " is Successfully Added!";
			}
			return "New Jobs is not Added!";

		} catch (Exception e) {
			e.getMessage();
		}
		return "New Jobs is not added!";

	}

	@Override
	public List<NewJobs> allJobs() {
		return this.newJobsRepo.findAll();

	}

	@Override
	public NewJobs getJob(Integer id) {
		NewJobs newJobs = this.newJobsRepo.findById(id).get();
		return newJobs;
	}

	@Override
	public String updateJob(NewJobs newJobs, Integer id) {

		try {
			if (this.newJobsRepo.existsById(id)) {
				newJobs.setId(id);
				this.newJobsRepo.save(newJobs);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

	@Override
	public String deleteJob(Integer id) {
		try {
			if (this.newJobsRepo.existsById(id)) {
				newJobsRepo.deleteById(id);
				return "Id no. " + id + " is deleted succesfully. ";
			} else {
				return "Id no. " + id + " does not exist. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

}
