package com.hrm.servicesImpls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.controllers.RegisterUserController;
import com.hrm.models.RegisterUserEntity;
import com.hrm.repositories.IRegisterUserRepository;
import com.hrm.services.IRegisterUserService;

@Service
public class RegisterUserServiceImpl implements IRegisterUserService {
	@Autowired
	IRegisterUserRepository userRepo;

	private static final Logger logger = LoggerFactory.getLogger(RegisterUserController.class);

	@Override
	public String registerUser(RegisterUserEntity user) {
		try {
			var registerUser = this.userRepo.save(user);
			if (registerUser.getId() > 0) {
				return "User Register Successfully of ID : " + registerUser.getId();
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}

		return "User Registration Failed";
	}

	@Override
	public boolean authenticateUser(RegisterUserEntity user) {
		logger.info("Inside Authenticate User method");

		boolean user1 = false;
		try {
			logger.debug("Email Id : {}, Password : {}", user.getEmailId(), user.getPassword());

			Object data = userRepo.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
			if (data != null)
				user1 = true;
			logger.info("User : {}", user1);

		} catch (Exception e) {
			logger.error("Error in Authenticating user : {}", e);

		}

		return user1;
	}

	@Override
	public RegisterUserEntity get(RegisterUserEntity request) {
		RegisterUserEntity entity = new RegisterUserEntity();

		String emailId = request.getEmailId();
		String password = request.getPassword();
		int id = request.getId();

		Optional<RegisterUserEntity> findById = this.userRepo.findById(id);

		RegisterUserEntity findByEmailIdAndPassword = this.userRepo.findByEmailIdAndPassword(emailId, password);
		System.out.println("findByEmailIdAndPassword: " + findByEmailIdAndPassword);

		System.out.println("findById :" + findById);
		return findByEmailIdAndPassword;
	}

}
