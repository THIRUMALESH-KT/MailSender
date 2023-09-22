package com.eidiko.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.dto.MailTransferDTO;
import com.eidiko.dto.UserDto;
import com.eidiko.entity.MailEntity;
import com.eidiko.service.MailService;

import jakarta.validation.Valid;
@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/Register")
    public ResponseEntity<MailEntity> Register(@Valid @RequestBody UserDto user) throws Exception{
		return new ResponseEntity<MailEntity>(mailService.register(user),HttpStatus.CREATED);	
    }
    @PutMapping("/update")
    public ResponseEntity<MailEntity> update(@Valid @RequestBody UserDto user, @RequestParam String mail) throws AccountNotFoundException{
    	return new ResponseEntity<MailEntity>(mailService.update(user,mail),HttpStatus.OK);
    }
    @GetMapping("/getEmailDetails")
    public ResponseEntity<MailEntity> finEmail(@RequestParam String mail) throws AccountNotFoundException{
    	return new ResponseEntity<MailEntity>(mailService.getMail(mail),HttpStatus.OK);
    }
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam("mail") String mail,@RequestParam("subject")String subject, @RequestParam("file") MultipartFile[] file) throws AccountNotFoundException, FileNotFoundException, FileUploadException {
    	mailService.sendEmail(mail,subject,file);
        return ResponseEntity.ok("Email sent successfully.");
    }
    @GetMapping("/historyByMailId")
    public ResponseEntity<List<MailTransferDTO>> getMailHistory(@RequestParam("mail") String mail) throws Exception{
		return new ResponseEntity<List<MailTransferDTO>>(mailService.getMailHistory(mail),HttpStatus.OK);
    	
    }
    
}
