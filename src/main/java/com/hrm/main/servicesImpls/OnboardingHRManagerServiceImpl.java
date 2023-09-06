package com.hrm.main.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.OnboardingHRManager;
import com.hrm.main.repositories.IOnboardingHRManagerRepository;
import com.hrm.main.services.IOnboardingHRManagerService;

@Service

public class OnboardingHRManagerServiceImpl implements IOnboardingHRManagerService {

	@Autowired
	private IOnboardingHRManagerRepository onboardingHRManagerRepository;

	@Override
	public String createHRManager(OnboardingHRManager hrManager) {
		try {
			var manager = this.onboardingHRManagerRepository.save(hrManager);
			if (manager.getId() > 0) {
				return "All detail are added : " + manager.getId();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "All details are not added";
	}

	@Override
	public List<OnboardingHRManager> getAllHRManager() {
		List<OnboardingHRManager> allmanager = onboardingHRManagerRepository.findAll();
		return allmanager;
	}

	@Override
	public OnboardingHRManager getHRManager(Integer id) {
		OnboardingHRManager manager = onboardingHRManagerRepository.findById(id).get();
		return manager;
	}

	@Override
	public String updateHRManager(OnboardingHRManager hrManager, Integer id) {

		try {

			if (this.onboardingHRManagerRepository.existsById(id)) {

				hrManager.setId(id);

				this.onboardingHRManagerRepository.save(hrManager);

				return "Id no. : " + id + " is updated ";

			} else {
				return "Id no. : " + id + " is does not exists ";
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return "Id no. :" + id + " is updated ";
	}

	@Override
	public String deleteHRManager(Integer id) {
		try {
			onboardingHRManagerRepository.deleteById(id);

			return "Id no. :" + id + " is deleted successfully";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. :" + id + " is not deleted";
	}

	@Override
	public Long nextValue() {
		return this.onboardingHRManagerRepository.count();
	}

}
