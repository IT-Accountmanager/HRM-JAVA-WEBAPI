package com.hrm.main.services;

import com.hrm.main.models.RegisterUserEntity;

public interface IRegisterUserService {

	String registerUser(RegisterUserEntity users);
	
	public boolean authenticateUser(RegisterUserEntity user);

}
