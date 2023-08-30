package com.hrm.main.servicesImpls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Regularization;
import com.hrm.main.repositories.IRegularizationRepository;
import com.hrm.main.services.IRegularizationService;

@Service
public class RegularizationServiceImpl implements IRegularizationService {

	@Autowired
	IRegularizationRepository regularizationRepo;

	@Override
	public String addRegularization(Regularization regularization) {
		try {

			Regularization addedRegularization = this.regularizationRepo.save(regularization);
			if (addedRegularization.getId() > 0) {
				return "Regularization of Id no. " + addedRegularization.getId() + " is Successfully Added!";
			}
			return "Regularization is not Added!";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Regularization is not added!";

	}

	@Override
	public List<Regularization> allRegularization() {

		return regularizationRepo.findAll();
	}

	@Override
	public Regularization getRegularization(Integer id) {
		return this.regularizationRepo.findById(id).get();
	}

	@Override
	public String deleteRegularization(Integer id) {
		try {
			this.regularizationRepo.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

	@Override
	public String editRegularization(Regularization regularization, Integer id) {

		try {
			if (this.regularizationRepo.existsById(id)) {
				regularization.setId(id);
				this.regularizationRepo.save(regularization);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

}
