package com.ricko.passwordmanager.Service;

import com.ricko.passwordmanager.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(SimpleMailMessage email){
        mailSender.send(email);
    }

    public SimpleMailMessage createEmailContent(@Valid User user, HttpServletRequest request){
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080";
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo( user.getEmail() );
        registrationEmail.setSubject( "Registration Confirmation" );
        registrationEmail.setText( "To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + user.getConfirmationToken() );
        registrationEmail.setFrom( "noreply@domain.com" );
        return registrationEmail;
    }
}
