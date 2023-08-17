package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.RegisterUserEntity;
import com.hrm.main.repositories.IRegisterUserRepository;
import com.hrm.main.services.IRegisterUserService;

@Service
public class RegisterUserServiceImpl implements IRegisterUserService {
	@Autowired
	IRegisterUserRepository userRepo;

	@Override
	public String registerUser(RegisterUserEntity user) {
		try {
			var registerUser = this.userRepo.save(user);
			if (registerUser.getId() > 0) {
				return "User Register Successfully with ID : " + registerUser.getId();
			}
		} catch (Exception ex) {
			return ex.getMessage();
		}

		return "User Registration Failed";
	}

	@Override
	public boolean authenticateUser(RegisterUserEntity user) {
		RegisterUserEntity user1 = userRepo.findByUserIdAndPassword(user.getEmailId(), user.getPassword());
		return user1 != null;
	}

}
