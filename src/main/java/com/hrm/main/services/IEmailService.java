package com.hrm.main.services;

import com.hrm.main.models.Email;

public interface IEmailService {


	String sendMailWithAttachment(Email email);
}
