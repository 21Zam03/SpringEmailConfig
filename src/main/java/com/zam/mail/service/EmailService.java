package com.zam.mail.service;

import com.zam.mail.payload.response.MessageResponse;
import jakarta.mail.MessagingException;

import java.io.File;

public interface EmailService {

    public MessageResponse sendEmail(String toUser, String subject, String message);
    public MessageResponse sendEmailWithFile(String toUser, String subject, String message, File file) throws MessagingException;

}
