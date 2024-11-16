package com.ohgiraffers.collatime.configuration.handler;

import com.ohgiraffers.collatime.visit.model.service.VisitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Configuration
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private VisitService visitService;

    public AuthSuccessHandler(VisitService visitService){
        this.visitService = visitService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        System.out.println("role: " + role);

        if(role.equals("ADMIN")) {
            response.sendRedirect("/admin/dashboard");
        }
        if(role.equals("USER")) {
            response.sendRedirect("/auth/loginresult");
        }

        visitService.loginUser();

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
