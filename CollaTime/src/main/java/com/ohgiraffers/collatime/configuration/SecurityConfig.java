package com.ohgiraffers.collatime.configuration;

import com.ohgiraffers.collatime.common.UserRole;
import com.ohgiraffers.collatime.configuration.handler.AuthFailHandler;
import com.ohgiraffers.collatime.configuration.handler.AuthSuccessHandler;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    //    비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    정적 리소스에 대한 내용을 제외하고 security 설정 -> static 하위 빼고
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }



    @Bean
    public SecurityFilterChain adminConfigure(HttpSecurity http) throws Exception {
        //        요청 권한 체크
        http.authorizeHttpRequests(auth -> {
            //            권한이 없어도 들어갈 수 있는 페이지
            auth.requestMatchers("/auth/*", "user/signup", "user/check", "/", "main", "mail/*", "/user/findIdAndPwd").permitAll();
            //            admin 권한이 있어야 들어갈 수 있음
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole());
            //            user 권한이 있어야 들어갈 수 있는 페이지
            auth.requestMatchers("/user/*","/project/*", "/schedule/*").hasAnyAuthority(UserRole.USER.getRole());
            //            인증이 있어야 들어갈 수 있다는 메소드
            auth.anyRequest().authenticated();
        }).formLogin(login -> {
            //            로그인 창이 있는 페이지 설정
            login.loginPage("/auth/login");
            //            유저네임(ID) 파라미터 키 지정
            login.usernameParameter("username");
            //            비밀번호 파라미터 키 지정
            login.passwordParameter("pass");
            //            로그인에 성공했을 때 들어갈 기본 페이지
//            login.defaultSuccessUrl("/project/projectmain");
            //            로그인 성공 핸들러
            login.successHandler(authSuccessHandler);
            //            로그인 실패 핸들러
            login.failureHandler(authFailHandler);
        }).logout(logout -> {
            //            로그아웃 요청에 대한 매핑
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            //            현재 로그인 되어 있는 쿠키 제거
            logout.deleteCookies("JSESSIONID");
            //            세션 제거
            logout.invalidateHttpSession(true);
            //            로그아웃 성공 시 이동할 url
            logout.logoutSuccessUrl("/");
        }).sessionManagement(session -> {
            //            최대 세션 수 설정 -> 세션은 어차피 1개밖에 못쓰니까 1로
            session.maximumSessions(1);
            //            세션 만료 시 이동할 url
            session.invalidSessionUrl("/");
            //            csrf 보안기능 비활성화 -> 나중에 활성화 시켜야했던 것 같기도.,..
        }).csrf(csrf -> csrf.disable());
        return http.build();
    }
}

