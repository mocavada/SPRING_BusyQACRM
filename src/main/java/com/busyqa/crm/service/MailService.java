package com.busyqa.crm.service;

import com.busyqa.crm.model.util.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private TemplateEngine templateEngine;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email) throws MailException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Testing Mail API");
        mail.setText("Hurray ! You have done that dude...");

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }

    public void sendEmailWithAttachment(String email) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String portalUrl = "http://localhost:4200/client/resetPassword/" + email;

        String messageBody = "Hello,\n" +
                "Please set up your profile in the following link: " + portalUrl + "\n" +
                "Firstly, you need to set up a new password for " +
                "the login credentials. The username is the email you have registered. Secondly, you need to " +
                "update your information by filling up the form. Please sign the payment plan agreement, and " +
                "upload it in the form. \n";


        helper.setTo(email);
        helper.setSubject("BusyQA Portal Registration And Payment Plan Agreement");
        helper.setText(messageBody);

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(mimeMessage);
    }



    // SEND EMAIL
    public ResponseEntity<?> sendWelcomePackageMail(Mail mail){
        sendWelcomePackage(mail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<?> sendPortalLinkMail(Mail mail){
        sendPortalLink(mail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public void sendPortalLink(Mail mail) {

        String messages = mail.getMessage();

        final Context context = new Context();


        context.setVariable("message", mail.getMessage());

        String[] messagesArray = messages.split("#");
        context.setVariable("firstname", messagesArray[0]);
        context.setVariable("accountLink", messagesArray[1]);

        String body = templateEngine.process("portal-link-template", context);
        sendPreparedMail(mail.getEmail(), mail.getSubject(), body, true);

    }


    // SEND EMAIL with TEMPLATE
    public void sendWelcomePackage(Mail mail) {

        String messages = mail.getMessage();
        String[] messagesArray = messages.split("#");

        //get and fill the template
        final Context context = new Context();

        context.setVariable("firstname", messagesArray[0]);
        context.setVariable("coursename", messagesArray[1]);
        context.setVariable("description", messagesArray[2]);
        context.setVariable("location", messagesArray[3]);
        context.setVariable("time", messagesArray[4]);
        context.setVariable("trainer", messagesArray[5]);
        context.setVariable("startDate", messagesArray[6]);
        context.setVariable("endDate", messagesArray[7]);
        context.setVariable("message", mail.getMessage());
        context.setVariable("welcomeheader", mail.getImageUrl());



        String body = templateEngine.process("welcome-package-template", context);
        //send the html template
        sendPreparedMail(mail.getEmail(), mail.getSubject(), body, true);
    }

    // RETURN EMAIL FORMAT
    private void sendPreparedMail(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error("Problem with sending email to: {}, error message: {}", to, e.getMessage());
        }
    }

    public void sendTemplatedEmailWithAttachment(String email, String fileNameComp) throws MailException, MessagingException {
        // the portal link
        String portalUrl = "http://localhost:4200/client/resetPassword/" + email;
        //get and fill the template
        final Context context = new Context();
        context.setVariable("portalUrl", portalUrl);
        String body = templateEngine.process("send-portal-link-email-template", context);
        //send the html template
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email);
        helper.setSubject("BusyQA Portal Registration And Payment Plan Agreement");
        helper.setText(body, true);
        String filePath = "/Users/mocavada/Documents/Learnings/Coop_BusyQA/uploads/planAgreement" + fileNameComp + ".pdf";
        FileSystemResource file = new FileSystemResource(new File(filePath));
        helper.addAttachment(file.getFilename(), file);
        javaMailSender.send(mail);
    }

}
