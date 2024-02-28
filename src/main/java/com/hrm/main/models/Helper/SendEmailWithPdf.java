/*
 * package com.hrm.main.models.Helper;
 * 
 * import javax.mail.*; import javax.mail.internet.*; import
 * java.io.ByteArrayOutputStream; import java.util.Properties;
 * 
 * public class SendEmailWithPdf { public static void
 * sendEmailWithAttachment(String toEmail, String subject, String body, byte[]
 * attachmentBytes) { final String username = "your-email@gmail.com"; final
 * String password = "your-email-password";
 * 
 * Properties props = new Properties(); props.put("mail.smtp.auth", "true");
 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.smtp.host",
 * "smtp.gmail.com"); props.put("mail.smtp.port", "587");
 * 
 * Session session = Session.getInstance(props, new Authenticator() { protected
 * PasswordAuthentication getPasswordAuthentication() { return new
 * PasswordAuthentication(username, password); } });
 * 
 * try { Message message = new MimeMessage(session); message.setFrom(new
 * InternetAddress(username)); message.setRecipients(Message.RecipientType.TO,
 * InternetAddress.parse(toEmail)); message.setSubject(subject);
 * 
 * BodyPart messageBodyPart = new MimeBodyPart(); messageBodyPart.setText(body);
 * 
 * Multipart multipart = new MimeMultipart();
 * multipart.addBodyPart(messageBodyPart);
 * 
 * // Add attachment messageBodyPart = new MimeBodyPart(); messageBodyPart
 * .setDataHandler(new DataHandler(new ByteArrayDataSource(attachmentBytes,
 * "application/pdf"))); messageBodyPart.setFileName("Appointment_Letter.pdf");
 * multipart.addBodyPart(messageBodyPart);
 * 
 * message.setContent(multipart);
 * 
 * Transport.send(message);
 * 
 * System.out.println("Email sent successfully!");
 * 
 * } catch (MessagingException e) { e.printStackTrace(); } }
 * 
 * public static void main(String[] args) { String toEmail =
 * "candidate@example.com"; String subject = "Your Appointment Letter"; String
 * body = "Dear Candidate,\n\nPlease find attached your appointment letter.";
 * 
 * // Convert your PDF content to byte array (replace this line with your actual
 * // conversion logic) byte[] pdfBytes = Convert your PDF content to byte array
 * "sample".getBytes();
 * 
 * sendEmailWithAttachment(toEmail, subject, body, pdfBytes); } }
 */