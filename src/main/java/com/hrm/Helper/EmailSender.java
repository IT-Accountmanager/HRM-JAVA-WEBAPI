/*
 * package com.hrm.main.models.Helper;
 * 
 * import java.util.Properties;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * jakarta.mail.Authenticator; import jakarta.mail.Message; import
 * jakarta.mail.PasswordAuthentication; import jakarta.mail.Session; import
 * jakarta.mail.Transport; import jakarta.mail.internet.InternetAddress; import
 * jakarta.mail.internet.MimeMessage;
 * 
 * public class EmailSender {
 * 
 * @Value("${spring.mail.username}") private String username;
 * 
 * @Value("${spring.mail.password}") private String password;
 * 
 * public boolean sendEmail(String to, String from, String subject, String body)
 * { boolean flag = false;
 * 
 * // SMTP PROPERTIES
 * 
 * Properties properties = new Properties(); properties.put("mail.smtp.auth",
 * true); properties.put("mail.smtp.starttls.enable", true);
 * properties.put("mail.smtp.port", "587"); properties.put("mail.smtp.host",
 * "smtp.gmail.com");
 * 
 * Session session = Session.getInstance(properties, new Authenticator() {
 * 
 * @Override protected PasswordAuthentication getPasswordAuthentication() {
 * return new PasswordAuthentication(username, password); } });
 * 
 * try { Message message = new MimeMessage(session);
 * 
 * message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
 * message.setFrom(new InternetAddress(from)); message.setSubject(subject);
 * message.setText(body);
 * 
 * Transport.send(message);
 * 
 * flag = true; } catch (Exception e) { e.printStackTrace(); } return flag; }
 * 
 * }
 */
package com;

