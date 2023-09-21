package com.eidiko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.dto.UserDto;
import com.eidiko.service.MailService;

import jakarta.validation.Valid;
@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam("mail") String mail,@RequestParam("file") MultipartFile file) {
        mailService.sendEmail(mail,file);
        return ResponseEntity.ok("Email sent successfully.");
    }
}
