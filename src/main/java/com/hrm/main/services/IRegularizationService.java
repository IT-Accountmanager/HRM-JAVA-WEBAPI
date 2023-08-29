package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Regularization;

public interface IRegularizationService {

	String addRegularization(Regularization regularization);

	List<Regularization> allRegularization();

	Regularization getRegularization(Integer id);

	String deleteRegularization(Integer id);

	String editRegularization(Regularization regularization, Integer id);
}
