package com.authservice.service;

import com.authservice.model.MyUser;

import javax.mail.MessagingException;

public interface IEmailService {

    void sendHTMLMail(MyUser user, String email) throws MessagingException;
}
