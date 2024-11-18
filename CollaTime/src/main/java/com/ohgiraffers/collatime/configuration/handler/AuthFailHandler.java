package com.ohgiraffers.collatime.configuration.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {


//    사용자의 잘못된 로그인 시도를 커스텀 사기 위한 핸들러이다.
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "";
        System.out.println("로그인 실패");
        System.out.println(exception.getMessage());
        if(exception instanceof UsernameNotFoundException) {
//            DB에 사용자의 정보가 없는 경우 발생하는 에러
            errorMessage = "존재하지 않는 ID입니다.";
        }else if(exception instanceof BadCredentialsException) {
//            사용자의 정보가 존재하지 않거나 비밀번호가 일치하지 않을때
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        }else if(exception instanceof InternalAuthenticationServiceException){
//            서버에서 사용자 정보를 검증하는 과정에서 발생하는 에러이다.
            errorMessage = "서버에서 오류가 발생하였습니다. 관리자에게 문의해주세요.";
        }else if (exception instanceof AuthenticationCredentialsNotFoundException){
//            보안 컨텍스트에 인증 객체가 존재하지 않거나 인증 정보가 없는 상태에서 보안처리된 리소스에 접근하는 경우 발생하는 에러이다.
            errorMessage = "인증 요청이 거부되었습니다.";
        }else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
        }

//        응답 메세지와 페이지 경로를 설정할 수 있게 하도록 재정의
//        URL을 안전하게 인코딩 하는데 사용되는 유틸로 문자열을 URL에 사용가능한 형식으로 인코딩 할 수 있다.
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        System.out.println(errorMessage);
//        setDefaultFailureUrl("/auth/fail?message=" + errorMessage);
        response.sendRedirect("/auth/loginfail?message="+errorMessage);

//        super.onAuthenticationFailure(request, response, exception);
//        ㄴ 로그인 실패 시 Cannot call sendError() after the response has been committed -> 무한 참조가 발생했다.
//        보안 실패시의 이유들이 계속 부딪히면서 일어난 일 인것 같다. 위의 부분을 주석처리하니 해결이 되었다.
    }
}
