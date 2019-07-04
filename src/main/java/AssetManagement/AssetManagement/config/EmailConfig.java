/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Okala
 */
@Configuration("emailConfig")
public class EmailConfig {

    private final String username = "upnormal.bootcamp@gmail.com";
    private final String password = "dan54bugi-bugi";

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Session getSession() {
        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }

    public boolean sendEmail(String email, String subject, String message) {
        try {
            Message msg = new MimeMessage(getSession());
            msg.setFrom(new InternetAddress(getUsername(), "EVAL"));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            msg.setSentDate(new Date());
            
            // Send the actual HTML message, as big as you like
            //message.setContent(readTxt(filePath),"text/html");
            Transport.send(msg);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
//    public boolean sendEmail(String email, String subject, String message, boolean useTemplate) {
//        try {
//            Message msg = new MimeMessage(getSession());
//            msg.setFrom(new InternetAddress(getUsername(), "EVAL"));
//
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//            msg.setSubject(subject);
//            if(useTemplate){
//            String filePath = "F:/eval/src/main/resources/static/dist/template/template.txt";
//            msg.setContent(readTxt(filePath),"text/html");
//            } else {
//            msg.setContent(message, "text/html");
//            }
//            msg.setSentDate(new Date());
            
            // Send the actual HTML message, as big as you like
            // Send the actual HTML message, as big as you like
            //message.setContent(readTxt(filePath),"text/html");
//            Transport.send(msg);
//            return true;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    
    public String readTxt(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public String createToken(int length) {
        String AlpaNumericString = ".0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY";
        String randomString = "";

        for (int i = 0; i < length; i++) {
            randomString += (AlpaNumericString.charAt(randomBetweenTwoNumber(0, AlpaNumericString.length() - 1)));
        }
        return randomString;
    }

    public int randomBetweenTwoNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min)) + min; //The maximum is exclusive and the minimum is inclusive
    }
}
