package com.hrm.main.servicesImpls;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.hrm.main.models.Email;
import com.hrm.main.services.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String sendSimpleMail(Email email) {
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(email.getRecipient());
			mailMessage.setSubject(email.getSubject());
			mailMessage.setText(email.getMsgBody());

			this.javaMailSender.send(mailMessage);
			return "Mail Sent Successfully.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error while Sending Mail.";
		}
	}

	@Override
	public String sendMailWithAttachment(Email email) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(email.getRecipient());
			mimeMessageHelper.setSubject(email.getSubject());
			mimeMessageHelper.setText(email.getMsgBody());

			FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getAttachment()));

			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

			javaMailSender.send(mimeMessage);
			return "Mail Sent Successfully";
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "Error while sending mail!!!";
	}

}
