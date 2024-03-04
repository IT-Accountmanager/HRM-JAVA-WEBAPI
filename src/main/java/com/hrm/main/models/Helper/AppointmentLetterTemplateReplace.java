package com.hrm.main.models.Helper;

import java.io.FileWriter;
import java.io.IOException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;

public class AppointmentLetterTemplateReplace {

	public static void processTemplateAndWriteToFile() {
		// Create a Thymeleaf template engine
		TemplateEngine templateEngine = new TemplateEngine();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateEngine.setTemplateResolver(templateResolver);

		// Set the placeholders in a Thymeleaf context
		Context context = new Context();
		context.setVariable("employeeId", "John Doe");
		context.setVariable("employeeName", "john@example.com");

		// Process the template
		String processedContent = templateEngine.process("template.html", context);

		// Write modified content to a new HTML file
		try (FileWriter writer = new FileWriter("output.html")) {
			writer.write(processedContent);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("HTML template has been processed successfully.");
	}

}
