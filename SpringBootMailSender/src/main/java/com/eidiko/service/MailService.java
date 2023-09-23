package com.eidiko.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import com.eidiko.dto.MailTransferDTO;
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


	    public void sendEmail(String mail,String subject,MultipartFile[] file) throws AccountNotFoundException, FileNotFoundException, FileUploadException {
	        MailEntity mailEntity = mailRepository.findByMail(mail);
	        if (mailEntity == null)throw new AccountNotFoundException("mail id not found");
	        if(file.length!=0) {
	        FileVlaidation.validate(file);
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
	            helper.setSubject(subject);
	            helper.setText(emailContent, true);
	            if(file.length!=0) {
	            	for(MultipartFile files:file)
	            helper.addAttachment(files.getOriginalFilename(), new ByteArrayResource(files.getBytes()));
	            }
	            javaMailSender.send(message);
	            MailTransferDetails details=new MailTransferDetails();
	            details.setToAddr(mail);
	            details.setSubject1(subject);
	            if(file.length!=0) {
	            	details.setFile(file);
	            	details.setFileNames(file);
	            	}
	            details.setTemplate(emailContent.getBytes());
	            details.setDate(new java.util.Date());
	            mailTransferDetailsRepository.save(details);
	        } catch (Exception e) {
	            // Handle exceptions, e.g., email sending failures
	            e.printStackTrace();
	        }
	    }

		public MailEntity register(@Valid UserDto user) throws Exception {
			// TODO Auto-generated method stub
			MailEntity mail=mailRepository.findByMail(user.getMail());
			if(mail!=null)throw new Exception("Dublicate Account");
			 mail=new MailEntity(user.getUserName(), user.getMail(), user.getMobile(), user.getPassword());
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

		public List<MailTransferDTO> getMailHistory(String mail) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("a");
			List<MailTransferDetails> lists=mailTransferDetailsRepository.findAllByToAddr(mail);
			System.out.println(lists);
			List<MailTransferDTO> dtolist=new ArrayList<>();
			for(MailTransferDetails list:lists) {
				MailTransferDTO dto=new MailTransferDTO(list.getId(), list.getToAddr(), list.getSubject1(), list.getDate(), list.getFileNames());
		dtolist.add(dto);
			}

			return dtolist;
		}
	

}
