package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Policies;

public interface IPoliciesService {

	String addPolicies(Policies policies);

	List<Policies> allPolicies();

	Policies getPolicies(Integer id);

	String updatePolicies(Policies policies, Integer id);

	String deletePolicies(Integer id);

}
