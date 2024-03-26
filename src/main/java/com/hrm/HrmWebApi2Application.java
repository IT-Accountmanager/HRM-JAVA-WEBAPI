package com.hrm;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//import com.hrm.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
//@ComponentScan("com.hrm.config")

@EnableScheduling
public class HrmWebApi2Application extends SpringBootServletInitializer {

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(HrmWebApi2Application.class); }
	 */

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HrmWebApi2Application.class);
	}

	/*
	 * @Autowired private TwilioConfig twilioConfig;
	 * 
	 * @PostConstruct public void setUp() {
	 * Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken()); }
	 */
	public static void main(String[] args) {
		SpringApplication.run(HrmWebApi2Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
