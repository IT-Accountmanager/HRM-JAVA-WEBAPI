package com.hrm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
//@ComponentScan("com.hrm.config")
@EnableTransactionManagement
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "API Documentation", version = "1.0", description = "API Documentation"))

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
		// gen();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	public static void gen() {
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String secret = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println("Secret : " + secret);
//	}

}
