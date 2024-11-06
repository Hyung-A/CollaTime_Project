package com.ohgiraffers.collatime.mail;

import com.ohgiraffers.collatime.project.model.service.ProjectService;
import com.ohgiraffers.collatime.user.model.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailService {

    private JavaMailSender mailSender;

    private UserService userService;

    private ProjectService projectService;

    @Autowired
    public MailService(JavaMailSender mailSender, UserService userService, ProjectService projectService) {

        this.mailSender = mailSender;
        this.userService = userService;
        this.projectService = projectService;
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

    private MimeMessage createMimeMessage(String mail, String code) throws MessagingException {


        MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, mail);
            mimeMessage.setSubject("안녕하세요! CollaTime 인증번호요청입니다.");
            mimeMessage.setFrom("gudjtr097@gmail.com");
            mimeMessage.setText(code);

        return mimeMessage;
    }

    private MimeMessage createPwdMessage(String mail, String code) throws MessagingException {


        MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, mail);
            mimeMessage.setSubject("안녕하세요! 회원님의 CollaTime 새 비밀번호입니다.");
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
        mailSender.send(mailForm);

        return mailDTO;
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
        System.out.println(newPwd);

        return newPwd;
    }

    public MailDTO sendPwd(String mail) throws MessagingException {
        String code = createPwd();
        MailDTO mailDTO = new MailDTO();

        int result = userService.modifyPwdByEmail(mail, code);

        if(result>0){
            mailDTO.setMail(mail);
            mailDTO.setCode(code);
            MimeMessage mailForm = createPwdMessage(mail, code);
            mailSender.send(mailForm);
        }
        return mailDTO;
    }

    /* project 참가 코드 메일 전송 */

//    // 랜덤 코드 발생
//    private String createJoinCode(){
//        int min = 48;
//        int max = 122;
//        int codeLength = 8;
//        Random random = new Random();
//
//        String randomJoinCode = random.ints(min, max + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(codeLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
////        System.out.println("코드" + randomJoinCode);
//
//        return randomJoinCode;
//    }

//    // 참가코드 메일 작성
//    public MimeMessage createEmailForm(String email, String joinCode) throws MessagingException{
//        MimeMessage message = mailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, email);
//        message.setFrom("gudjtr097@gmail.com");
//        message.setSubject("안녕하세요! CollaTime 프로젝트 참가 인증 코드입니다.");
//        joinCode = createJoinCode();
//        message.setText(joinCode);
//
//        return message;
//    }

    // 참가 코드 전송
//    public MailDTO sendMailJoinCode(String data) throws MessagingException{
//        String joinCode = createJoinCode();
//        MailDTO mailDTO = new MailDTO();
//        mailDTO.setMail(data);
//        mailDTO.setCode(joinCode);
//        MimeMessage mailForm = createMimeMessage(data, joinCode);
//        mailSender.send(mailForm);
//
//        return mailDTO;
//    }
}
