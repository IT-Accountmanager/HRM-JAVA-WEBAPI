package com.hrm.main.services;

import com.hrm.main.models.User;

public interface UserService {

	User addUser(User user);

	String checkUser(String email, String pass);

}
