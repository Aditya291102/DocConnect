package com.hms.entity;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    public static boolean sendEmail(String toEmail, String subject, String messageBody) {
        boolean flag = false;

        // Sender's email credentials
        String fromEmail = "khamkarsurekha08@gmail.com"; // Replace with your email
        String password = "eioo stov tavv bcxx";    // Replace with your email password

        // SMTP server configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Use Gmail's SMTP server
        props.put("mail.smtp.port", "587");           // TLS port
        props.put("mail.smtp.auth", "true");          // Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Create a session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            // Send the email
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
    System.out.println("Email send failed: " + e.getMessage());
    e.printStackTrace();
}


        return flag;
    }
}
