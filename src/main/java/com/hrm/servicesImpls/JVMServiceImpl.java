package com.hrm.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.JVM;
import com.hrm.models.NewJobs;
import com.hrm.repositories.IJVMRepository;
import com.hrm.services.IJVMService;

@Service
public class JVMServiceImpl implements IJVMService {

	@Autowired
	IJVMRepository jvmRepo;

	@Override
	public String addJvm(JVM jvm) {
		try {

			JVM save = this.jvmRepo.save(jvm);
			if (save.getId() > 0) {
				return "JVM of Id no. " + save.getId() + " is Successfully Added!";
			}
			return "JVM is not Added!";

		} catch (Exception e) {
			e.getMessage();
		}
		return "JVM is not added!";

	}

	@Override
	public List<JVM> allJvm() {
		return this.jvmRepo.findAll();
	}

	@Override
	public JVM getJvm(Integer id) {
		JVM jvm = this.jvmRepo.findById(id).get();
		return jvm;
	}

	@Override
	public String updateJvm(JVM jvm, Integer id) {

		try {
			if (this.jvmRepo.existsById(id)) {
				jvm.setId(id);
				this.jvmRepo.save(jvm);
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
	public String deleteJvm(Integer id) {
		try {
			if (this.jvmRepo.existsById(id)) {
				jvmRepo.deleteById(id);
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
