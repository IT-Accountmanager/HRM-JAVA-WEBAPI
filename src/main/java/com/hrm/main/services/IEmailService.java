package com.hrm.main.services;

import com.hrm.main.models.Email;

public interface IEmailService {

	String sendSimpleMail(Email email);

	String sendMailWithAttachment(Email email);
}
