package com.hrm.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.models.Policies;
import com.hrm.repositories.IPoliciesRepository;
import com.hrm.services.IPoliciesService;

@Service
public class PoliciesServiceImpl implements IPoliciesService {
	@Autowired
	IPoliciesRepository policiesRepo;

	@Override
	public String addPolicies(Policies policies) {
		try {

			Policies save = this.policiesRepo.save(policies);
			if (save.getId() > 0) {
				return "Policies of Id no. " + save.getId() + " is Successfully Added!";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Policies is not added!";

	}

	@Override
	public List<Policies> allPolicies() {
		return this.policiesRepo.findAll();
	}

	@Override
	public Policies getPolicies(Integer id) {
		Policies policies = this.policiesRepo.findById(id).get();
		return policies;
	}

	@Override
	public String updatePolicies(Policies policies, Integer id) {

		try {
			if (this.policiesRepo.existsById(id)) {
				policies.setId(id);
				this.policiesRepo.save(policies);
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
	public String deletePolicies(Integer id) {
		try {
			if (this.policiesRepo.existsById(id)) {
				policiesRepo.deleteById(id);
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
