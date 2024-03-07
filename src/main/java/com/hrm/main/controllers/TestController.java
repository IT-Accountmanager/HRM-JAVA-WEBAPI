package com.hrm.main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/testWorkStatus")
	public String testWorkStatus() {
		return "working fine";
	}
}