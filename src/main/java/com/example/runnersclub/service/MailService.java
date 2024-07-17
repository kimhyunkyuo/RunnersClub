package com.example.runnersclub.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "khg1222@gmail.com";
    private static int number;

    public static void createNumber(){
        number = (int) (Math.random() * (90000)) + 100000;
    }
    public MimeMessage createMail(String mail){{

    }
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h1>" + "RunnersClub" + "</h1>";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h2>" + number + "</h2>";
            body += "<h3>" + "인증번호 입력란에 인증번호를 입력해주시기바랍니다" + "</h3>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return message;
    }
     public int sendMail(String mail){
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);
        return number;
     }
}
