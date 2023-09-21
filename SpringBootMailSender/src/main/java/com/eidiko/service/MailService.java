package com.eidiko.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import com.eidiko.dto.UserDto;
import com.eidiko.entity.MailEntity;
import com.eidiko.repository.MailRepository;

import ch.qos.logback.core.Context;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService implements IMailService {

	    @Autowired
	    private org.springframework.mail.javamail.JavaMailSender javaMailSender;

	    @Autowired
	    private MailRepository mailRepository;

	    @Autowired
	    private TemplateEngine templateEngine;

	    public void sendEmail(String mail,MultipartFile file) {
	        // Retrieve user's email from the database based on the provided mail
//	    	MailEntity mailob=new MailEntity();
//	    	mailob.setMail("tthirumal24@gmail.com");
//	    	mailob.setMobile(7013231552l);
//	    	mailob.setPassword("Thiru@9494");
//	    	mailob.setUserName("MaheshBabu");
//	    	mailRepository.save(mailob);
	        MailEntity mailEntity = mailRepository.findByMail(mail);
	        
	        if (mailEntity == null) {
	            // Handle the case when the email is not found in the database
	            return;
	        }

	        // Create a context to populate dynamic content in the template
	        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
	        context.setVariable("userName", mailEntity.getUserName());

	        // Process the email template
	        String emailContent = templateEngine.process("email-template.html", context);

	        // Send the email
	        try {
	        	
	            MimeMessage message = javaMailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setTo(mail);
	            helper.setSubject("Sample Email Subject");
	            helper.setText(emailContent, true);
	            helper.addAttachment(file.getOriginalFilename(), file);
	            javaMailSender.send(message);
	        } catch (Exception e) {
	            // Handle exceptions, e.g., email sending failures
	            e.printStackTrace();
	        }
	    }
	

}
