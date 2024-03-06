package com.hrm.services;

import java.util.List;

import com.hrm.models.Policies;

public interface IPoliciesService {

	String addPolicies(Policies policies);

	List<Policies> allPolicies();

	Policies getPolicies(Integer id);

	String updatePolicies(Policies policies, Integer id);

	String deletePolicies(Integer id);

}
