/*
 * package com.hrm.main.models.Helper;
 * 
 * import java.io.BufferedReader; import java.io.InputStreamReader; import
 * java.net.HttpURLConnection; import java.net.URL; import java.net.URLEncoder;
 * 
 * public class SendSMS {
 * 
 * public static void sendSMS(String message, long contactNumber) {
 * System.out.println("In send SmS method"); try {
 * System.out.println("In send SmS method - in try {}");
 * 
 * String apiKey =
 * "iA9jUEtqM3F5WxoNcRbCaG4rKQynV7dwTfDgP1lpsXIB8SHYmLj5J9tc7R2lEshGTNMAmpq8DafUrQxF";
 * // String senderId = ""; message = URLEncoder.encode(message, "UTF-8");
 * String language = "english"; String route = "q"; String myUrl =
 * "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&message=" +
 * message + "&language=" + language + "&route=" + route + "&numbers=" +
 * contactNumber;
 * 
 * URL url = new URL(myUrl); HttpURLConnection con = (HttpURLConnection)
 * url.openConnection();
 * 
 * con.setRequestMethod("GET");
 * 
 * con.setRequestProperty("User-Agent", "Mozilla/5.0");
 * con.setRequestProperty("cache-control", "no-cache");
 * 
 * int responseCode = con.getResponseCode();
 * 
 * StringBuffer response = new StringBuffer();
 * 
 * BufferedReader br = new BufferedReader(new
 * InputStreamReader(con.getInputStream()));
 * 
 * System.out.println("__________________________________________________");
 * 
 * System.out.println("Response : " + response);
 * System.out.println("Response Code : " + responseCode);
 * 
 * System.out.println("__________________________________________________");
 * while (true) { String line = br.readLine(); if (line == null) { break; }
 * response.append(line);
 * 
 * }
 * 
 * } catch (Exception e) { System.out.println("In catch"); e.getStackTrace();
 * System.out.println(e.getStackTrace()); }
 * 
 * } }
 */

package com.hrm.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SendSMS {
	/*
	 * 
	 * private static final Logger logger = LoggerFactory.getLogger(SendSMS.class);
	 * 
	 * public static void sendSMS(String message, long contactNumber) { int
	 * responseCode = -1; // Initialize responseCode outside the try block
	 * 
	 * try { String apiKey =
	 * "iA9jUEtqM3F5WxoNcRbCaG4rKQynV7dwTfDgP1lpsXIB8SHYmLj5J9tc7R2lEshGTNMAmpq8DafUrQxF";
	 * message = URLEncoder.encode(message, "UTF-8"); String language = "english";
	 * String route = "q"; String myUrl =
	 * "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&message=" +
	 * message + "&language=" + language + "&route=" + route + "&numbers=" +
	 * contactNumber;
	 * 
	 * logger.info("SMS API Request URL: {}", myUrl);
	 * 
	 * URL url = new URL(myUrl); HttpURLConnection con = (HttpURLConnection)
	 * url.openConnection();
	 * 
	 * con.setRequestMethod("GET");
	 * 
	 * con.setRequestProperty("User-Agent", "Mozilla/5.0");
	 * con.setRequestProperty("cache-control", "no-cache");
	 * 
	 * responseCode = con.getResponseCode(); // Assign the value here
	 * 
	 * StringBuffer response = new StringBuffer();
	 * 
	 * try (BufferedReader br = new BufferedReader(new
	 * InputStreamReader(con.getInputStream()))) { while (true) { String line =
	 * br.readLine(); if (line == null) { break; } response.append(line); } }
	 * 
	 * logger.info("SMS API Response Code: {}", responseCode);
	 * logger.info("SMS API Response: {}", response.toString());
	 * 
	 * } catch (Exception e) { logger.error("Failed to send SMS", e);
	 * logger.error("API Response Code: {}", responseCode); // Now it's accessible
	 * in the catch block } }
	 */

	private static final Logger logger = LoggerFactory.getLogger(SendSMS.class);

	// ... other methods

	/*
	 * public static void sendSMS(String myUrl) throws IOException {
	 * 
	 * String baseUrl = "yourBaseUrl"; // Replace with your actual base URL String
	 * authorizationKey = "yourAuthorizationKey"; // Replace with your actual
	 * authorization key String orignalMessage = "Hello"; String language =
	 * "english"; String route = "q"; String numbers = "9096643789"; try { String
	 * message = URLEncoder.encode("Hello", StandardCharsets.UTF_8.toString());
	 * String completeUrl = myUrl + "?authorization=yourAuthorizationKey&message=" +
	 * message + "&language=english&route=q&numbers=9096643789"; URL url = new
	 * URL(completeUrl);
	 * 
	 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 * conn.setRequestMethod("GET");
	 * 
	 * int responseCode = conn.getResponseCode();
	 * logger.info("API Response Code: {}", responseCode);
	 * 
	 * if (responseCode == HttpURLConnection.HTTP_OK) { try (BufferedReader in = new
	 * BufferedReader(new InputStreamReader(conn.getInputStream()))) { String
	 * inputLine; StringBuilder response = new StringBuilder();
	 * 
	 * while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
	 * 
	 * // Log the response if needed logger.info("API Response: {}",
	 * response.toString()); } } else {
	 * logger.error("Failed to send SMS. HTTP response code: {}", responseCode); } }
	 * catch (Exception e) { logger.error("Failed to send SMS", e); throw new
	 * IOException("Failed to send SMS", e); } }
	 */

	// -___________________________________________________________________________

	public static void sendSMS(String myUrl) throws IOException {
		// Constants for base URL and authorization key
		final String BASE_URL = "https://www.fast2sms.com/dev/bulkV2"; // Replace with your actual base URL
		final String AUTHORIZATION_KEY = "iA9jUEtqM3F5WxoNcRbCaG4rKQynV7dwTfDgP1lpsXIB8SHYmLj5J9tc7R2lEshGTNMAmpq8DafUrQxF"; // Replace
																																// with
																																// your
																																// actual
																																// authorization
																																// key

		try {
			if (!myUrl.startsWith("http://") && !myUrl.startsWith("https://")) {
				myUrl = "http://" + myUrl;
			}

			String originalMessage = "Hello";
			String encodedMessage = URLEncoder.encode(originalMessage, StandardCharsets.UTF_8.toString());
			String sender_id = "FSTSMS";

			// Construct the complete URL using StringBuilder
			StringBuilder urlBuilder = new StringBuilder(BASE_URL);
			urlBuilder.append("?authorization=").append(AUTHORIZATION_KEY).append("&sender_id=").append(sender_id)
					.append("&message=").append(encodedMessage).append("&language=english&route=q&numbers=9096643789");

			URL url = new URL(urlBuilder.toString());
			System.out.println("_________________________________________________________");
			System.out.println("URL : " + url);
			System.out.println("_________________________________________________________");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();
			logger.info("API Response Code: {}", responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
					String inputLine;
					StringBuilder response = new StringBuilder();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}

					// Log the response if needed
					logger.info("API Response: {}", response.toString());
				}
			} else {
				logger.error("Failed to send SMS. HTTP response code: {}", responseCode);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("Failed to encode the message", e);
			throw new IOException("Failed to send SMS", e);
		} catch (MalformedURLException e) {
			logger.error("Malformed URL", e);
			throw new IOException("Failed to send SMS", e);
		} catch (IOException e) {
			logger.error("Failed to send SMS", e);
			throw new IOException("Failed to send SMS", e);
		}
	}

}
