package com.ohgiraffers.collatime.mail.model.service;

import com.ohgiraffers.collatime.mail.model.dto.MailDTO;
import com.ohgiraffers.collatime.project.controller.ProjectController;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import com.ohgiraffers.collatime.user.model.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

@Service
public class MailService {

    private JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    private UserService userService;


    @Autowired
    public MailService(JavaMailSender mailSender, UserService userService, SpringTemplateEngine templateEngine) {

        this.mailSender = mailSender;
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    public MailDTO sendSignUp(String email, String purpose){
        String code = createCode();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject("안녕하세요! CollaTime 인증번호요청입니다."); // 메일 제목
            mimeMessageHelper.setText(setContext(code ,purpose), true); // 메일 본문 내용, HTML 여부
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MailDTO mailDTO = new MailDTO();
        mailDTO.setMail(email);
        mailDTO.setCode(code);

        return mailDTO;
    }

    private void createPwdMessage(String mail, String code) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(mail); // 메일 수신자
            mimeMessageHelper.setSubject("안녕하세요! 회원님의 CollaTime 새 비밀번호입니다."); // 메일 제목
            mimeMessageHelper.setText(setPwdContext(code), true); // 메일 본문 내용, HTML 여부
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public MailDTO sendPwd(String mail) throws MessagingException {
        String code = createPwd();
        MailDTO mailDTO = new MailDTO();

        int result = userService.modifyPwdByEmail(mail, code);

        if(result>0){
            mailDTO.setMail(mail);
            mailDTO.setCode(code);
            createPwdMessage(mail, code);
        }
        return mailDTO;
    }

//    랜덤코드 함수 -> 사이트보고 참조함
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

    private String createPwd(){
        String newPwd = "";

        for(int i =0;i <= 8; i++) {
            int categoryRandom = (int) (Math.random() * 5) + 1;
            if (categoryRandom == 1) {
                int signRandom = (int) (Math.random() * 6) + 33;
                newPwd += (char)signRandom;
            } else if (categoryRandom == 2 || categoryRandom == 3) {
                int numRandom = (int) (Math.random() * 10) + 48;
                newPwd += (char)numRandom;
            } else {
                int charRandom = (int) (Math.random() * 26) + 97;
                newPwd += (char) charRandom;
            }
        }

        return newPwd;
    }

    public String setContext(String code, String purpose) {
        Context context = new Context();
        context.setVariable("purpose", purpose);
        context.setVariable("code", code);
        return templateEngine.process("/mail/codeMail", context);
    }

    public String setPwdContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("/mail/newPwdMail", context);
    }

    /* ------------------ 프로젝트 ------------------ */
    // joinCodeMailForm만들기
    private MimeMessage createJoinCodeMessage(String email, String joinCode) throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.addRecipients(MimeMessage.RecipientType.TO, email);
        mimeMessage.setSubject("안녕하세요! 회원님의 CollaTime 프로젝트 참가 코드 번호 입니다.");
        mimeMessage.setFrom("gudjtr097@gmail.com");
        mimeMessage.setText(joinCode);

        return mimeMessage;
    }

    // 프로젝트 조인 코드 보내기
    public MailDTO sendJoinCodeMail(String email, String joinCode) throws MessagingException{
        MailDTO mailDTO = new MailDTO();
        mailDTO.setMail(email);
        mailDTO.setCode(joinCode);
        MimeMessage joinCodeMailForm = createJoinCodeMessage(email, joinCode);
        mailSender.send(joinCodeMailForm);

        return mailDTO;
    }

}
