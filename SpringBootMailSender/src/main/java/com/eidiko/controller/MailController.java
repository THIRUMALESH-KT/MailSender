package com.eidiko.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String,Object>> Register(@Valid @RequestBody UserDto user) throws Exception{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", mailService.register(user));
		map.put("status", String.valueOf(HttpStatus.CREATED));

		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> update(@Valid @RequestBody UserDto user, @RequestParam String mail) throws AccountNotFoundException{
    	Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", mailService.update(user, mail));
		map.put("status", String.valueOf(HttpStatus.OK));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);   
		}
    @GetMapping("/getEmailDetails")
    public ResponseEntity<Map<String,Object>> finEmail(@RequestParam String mail) throws AccountNotFoundException{
    	Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", mailService.getMail(mail));
		map.put("status", String.valueOf(HttpStatus.OK));

		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);    }
    @PostMapping("/sendEmail")
    public ResponseEntity<Map<String,Object>> sendEmail(@RequestParam("mail") String mail,@RequestParam("subject")String subject, @RequestParam("file") MultipartFile[] file) throws AccountNotFoundException, FileNotFoundException, FileUploadException {
    	Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		mailService.sendEmail(mail, subject, file);
		map.put("message", "mail sucefully sent");
		map.put("status", String.valueOf(HttpStatus.OK));

		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
    @GetMapping("/historyByMailId")
    public ResponseEntity<Map<String,Object>> getMailHistory(@RequestParam("mail") String mail) throws Exception{
    	Map<String,Object> map=new HashMap<>();
		map.put("result", "success");
		map.put("message", mailService.getMailHistory(mail));
		map.put("status", String.valueOf(HttpStatus.OK));

		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);    	
    }
    
}
