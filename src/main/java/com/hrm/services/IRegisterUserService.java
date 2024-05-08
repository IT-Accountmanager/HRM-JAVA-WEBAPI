package com.hrm.services;

import com.hrm.models.RegisterUserEntity;

public interface IRegisterUserService {

	String registerUser(RegisterUserEntity users);
	
	public boolean authenticateUser(RegisterUserEntity user);

	RegisterUserEntity get(RegisterUserEntity request);

}
