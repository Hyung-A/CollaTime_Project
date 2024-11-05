package com.ohgiraffers.collatime.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Repository
public class MailService {

    @Autowired
    private final JavaMailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private String createCode(){

        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 | i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private MimeMessage createMimeMessage(String mail, String code) throws MessagingException {


        MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, mail);
            mimeMessage.setSubject("안녕하세요! CollaTime 인증번호요청입니다.");
            mimeMessage.setFrom("gudjtr097@gmail.com");
            mimeMessage.setText(code);

        return mimeMessage;
    }

    public MailDTO sendMail(String data) throws MessagingException {
        String code = createCode();
        MailDTO mailDTO = new MailDTO();
        mailDTO.setMail(data);
        mailDTO.setCode(code);
        MimeMessage mailForm = createMimeMessage(data, code);
        javaMailSender.send(mailForm);

        return mailDTO;
    }
}
