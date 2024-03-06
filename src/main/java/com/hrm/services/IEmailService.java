package com.hrm.services;

import com.hrm.models.Email;

public interface IEmailService {


	String sendMailWithAttachment(Email email);
}
