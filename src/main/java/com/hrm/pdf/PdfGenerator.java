package com.hrm.pdf;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component

public class PdfGenerator {

	private String htmlToXhtml(String html) {

		// converting html to xhtml
		Document document = Jsoup.parse(html);
		document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
		return document.html();
	}

	public void generatePdf(String content, String path) {
		/*
		 * String inputFile = "appointment_letter123.html"; // Path to your XHTML/XML
		 * file String outputFile = "output.pdf"; // Path to the output PDF file
		 */
		try {
			// Create an ITextRenderer instance
			ITextRenderer renderer = new ITextRenderer();

			// Set the input document

			/*
			 * Map<String, String> valueMap = new HashMap<>();
			 * 
			 * valueMap.put("employeeId", "20240200001");
			 * 
			 * valueMap.put("employeeName", "Harish Jay Raj");
			 * 
			 * String content = FileUtils.readFileToString(Paths.get(inputFile).toFile(),
			 * StandardCharsets.UTF_8);
			 */
			/*
			 * Set<Entry<String, String>> entrySet = valueMap.entrySet(); // Perform
			 * replacements for (Entry<String, String> es : entrySet) { content =
			 * content.replace("@{" + es.getKey() + "}", es.getValue()); }
			 */
			//

			String htmlToXhtml = htmlToXhtml(content);
			renderer.setDocumentFromString(htmlToXhtml);

			// Render the document to PDF
			renderer.layout();
			FileOutputStream fos = new FileOutputStream(path);
			renderer.createPDF(fos);
			fos.close();

			System.out.println("PDF generated successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
