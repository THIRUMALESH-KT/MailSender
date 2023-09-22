package com.eidiko.service;

import java.io.FileNotFoundException;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import com.eidiko.dto.UserDto;
import com.eidiko.entity.MailEntity;
import com.eidiko.entity.MailTransferDetails;
import com.eidiko.repository.MailRepository;
import com.eidiko.repository.MailTransferDetailsRepository;
import com.eidiko.validation.FileVlaidation;

import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;

@Service
public class MailService implements IMailService {

	    @Autowired
	    private org.springframework.mail.javamail.JavaMailSender javaMailSender;

	    @Autowired
	    private MailRepository mailRepository;
	    @Autowired
	    private MailTransferDetailsRepository mailTransferDetailsRepository;
	    @Autowired
	    private TemplateEngine templateEngine;

	    public void sendEmail(String mail,String subject,MultipartFile file) throws AccountNotFoundException, FileNotFoundException, FileUploadException {
	        MailEntity mailEntity = mailRepository.findByMail(mail);
	        
	        if (mailEntity == null)throw new AccountNotFoundException("mail id not found");
	        FileVlaidation.validate(file);
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
	            helper.setSubject(subject);
	            helper.setText(emailContent, true);
	            helper.addAttachment(file.getOriginalFilename(), file);
	            javaMailSender.send(message);
	            MailTransferDetails details=new MailTransferDetails();
	            details.setToAddr(mail);
	            details.setSubject1(subject);
	            details.setFile(file.getBytes());
	            details.setTemplate(emailContent.getBytes());
	            details.setFileName(file.getOriginalFilename());
	            details.setDate(new java.util.Date());
	            mailTransferDetailsRepository.save(details);
	        } catch (Exception e) {
	            // Handle exceptions, e.g., email sending failures
	            e.printStackTrace();
	        }
	    }

		public MailEntity register(@Valid UserDto user) {
			// TODO Auto-generated method stub
			MailEntity mail=new MailEntity(user.getUserName(), user.getMail(), user.getMobile(), user.getPassword());
			return mailRepository.save(mail);
		}

		public MailEntity update(@Valid UserDto user, String mail2) throws AccountNotFoundException {
			// TODO Auto-generated method stub
			MailEntity mail=mailRepository.findByMail(mail2);
			if(mail==null)throw new AccountNotFoundException("mail not found");
			mail.setMail(user.getMail());
			mail.setMobile(user.getMobile());
			mail.setPassword(user.getPassword());
			mail.setUserName(user.getUserName());
			return mailRepository.save(mail);
		}

		public MailEntity getMail(String mail) throws AccountNotFoundException {
			// TODO Auto-generated method stub
			MailEntity mailEntity=mailRepository.findByMail(mail);
			if(mailEntity==null) throw new AccountNotFoundException("mail not found");
			return mailRepository.findByMail(mail);
		}
	

}
