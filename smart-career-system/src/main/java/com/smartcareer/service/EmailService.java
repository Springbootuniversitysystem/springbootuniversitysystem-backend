package com.smartcareer.service;

import com.smartcareer.entity.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMessage(ContactMessage message) {

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo("emanuelmafalo@gmail.com"); // Replace with your email
        email.setSubject("New Contact Message from " + message.getName());
        email.setFrom("noreply@demomailtrap.co");
        email.setReplyTo(message.getEmailAddress());
        email.setSubject(message.getSubject());
        email.setText("Name : "+ message.getName() + "\n"+
                       "Email : " + message.getEmailAddress()+  "\n"+
                       "Message : " +message.getMessage());


        mailSender.send(email);
    }
}
