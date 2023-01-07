package com.authservice.service;

import com.authservice.model.MyUser;
import com.authservice.model.VerificationToken;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {

    private final VerificationTokenService verificationTokenService;
    private final TemplateEngine templateEngine;        // za generisanje izgleda mejla (u html-u) -> template/verification.html
    private final JavaMailSender javaMailSender;

    public EmailService(VerificationTokenService verificationTokenService, TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendHTMLMail(MyUser user, String email) throws MessagingException {

        VerificationToken verificationToken = this.verificationTokenService.findByUser_Username(user.getUsername());
        if(verificationToken != null){
            String token = verificationToken.getToken();
            Context context = new Context();
            context.setVariable("title", "Verify your email address ");

            context.setVariable("link", "http://localhost:4200/email-verification?token="+token+"&username="+user.getUsername());

            String body = templateEngine.process("verification", context);

            //Send verification email
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("socialnetworkSMPOUS@outlook.com");
            helper.setTo(email);
            helper.setSubject("Email verification");
            helper.setText(body, true);
            javaMailSender.send(message);

        }
    }
}
