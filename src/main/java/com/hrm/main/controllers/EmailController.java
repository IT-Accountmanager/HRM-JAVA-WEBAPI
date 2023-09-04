package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Email;
import com.hrm.main.services.IEmailService;

@RestController
public class EmailController {

	@Autowired
	IEmailService emailService;

	@PostMapping("/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody Email email) {
		String result = this.emailService.sendSimpleMail(email);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping("/sendMailAttachment")
	public ResponseEntity<String> sendMailWithAttachment(@RequestBody Email email) {
		String sendMailWithAttachment = this.emailService.sendMailWithAttachment(email);
		return new ResponseEntity<String>(sendMailWithAttachment, HttpStatus.OK);

	}

}
