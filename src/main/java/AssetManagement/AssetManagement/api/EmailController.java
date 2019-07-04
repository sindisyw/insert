/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Okala
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    private final String username = "upnormal.bootcamp@gmail.com";
    private final String password = "dan54bugi-bugi";

    //Forget Password
    @GetMapping("/forget/password")
    public String index(@PathVariable String email) {
        if (sendEmail(email, "EVAL", "Forget Password")) {
            return "redirect:/forget/?fail";
        }

        return "forget/password/success";
    }

    private boolean sendEmail(String email, String subject, String message) {
        try {
            Message msg = new MimeMessage(session());
//            msg.setFrom(new InternetAddress(username, "EVAL"));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            msg.setSentDate(new Date());

            // Send the actual HTML message, as big as you like
            //message.setContent(readTxt(filePath),"text/html");
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean sendEmailTemplate(String email, String subject, String message, String templateName) {
        try {
            Message msg = new MimeMessage(session());
            msg.setFrom(new InternetAddress());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(subject);
            //String filePath = "c:/temp/data.txt";
            String filePath = "F:/NetBeansProject/OJDBC/src/tools/" + templateName + ".txt";

            // Send the actual HTML message, as big as you like
            msg.setContent(readTxt(filePath), "text/html");
            msg.setSentDate(new Date());

            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readTxt(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private final Session session() {
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
}
