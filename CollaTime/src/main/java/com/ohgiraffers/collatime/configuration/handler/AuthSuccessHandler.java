//package com.ohgiraffers.collatime.configuration.handler;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.Iterator;
//
//@Configuration
//public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        System.out.println("authentication: " + authentication.getName());
//
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
//        GrantedAuthority auth = iter.next();
//        String role = auth.getAuthority();
//        System.out.println("role: " + role);
//
//        if(role.equals("ADMIN")) {
//            System.out.println("admin");
//            response.sendRedirect("/admin/dashbord");
//        }
//        if(role.equals("USER")) {
//            System.out.println("user");
//            response.sendRedirect("/project/ProjectMain");
//        }
//
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}
