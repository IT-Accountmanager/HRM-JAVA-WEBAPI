package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.User;
import com.hrm.main.services.UserService;

@RestController
@RequestMapping(value = "/User")

public class UserController {
	@Autowired
	UserService userServ;

	@PostMapping("/")
	public String addUser(@RequestBody User user) {
		this.userServ.addUser(user);
		return "User Added Successfully";
	}

	@GetMapping("/{email},{pass}")
	public User checkUser(@RequestParam String email, @RequestParam String pass) {
		this.userServ.checkUser(email, pass);
		return null;
	}
}
