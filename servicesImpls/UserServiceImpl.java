package com.hrm.main.servicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.main.models.User;
import com.hrm.main.repositories.UserRepository;
import com.hrm.main.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public User addUser(User user) {
		User addedUser = this.userRepo.save(user);
		return addedUser;
	}

	@Override
	public String checkUser(String email, String pass) {
		// this.userRepo.exists(email);
		return null;
	}

}
