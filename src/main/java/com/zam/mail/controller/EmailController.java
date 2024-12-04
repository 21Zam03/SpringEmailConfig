package com.zam.mail.controller;

import com.zam.mail.payload.request.EmailRequest;
import com.zam.mail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping(EmailController.API_PATH)
public class EmailController {

    public static final String API_PATH = "/api/email";

    public EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        return new ResponseEntity<>(emailService.sendEmail(emailRequest.getToUser(), emailRequest.getSubject(),
                emailRequest.getMessage()), HttpStatus.OK);
    }

    @PostMapping(value="/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendEmailWithFile(
            @RequestParam("toUser") String toUser,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            @RequestParam("file") File file) throws MessagingException {
        return new ResponseEntity<>(emailService.sendEmailWithFile(toUser, subject, message, file), HttpStatus.OK);
    }

}
