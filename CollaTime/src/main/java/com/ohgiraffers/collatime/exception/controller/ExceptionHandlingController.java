package com.ohgiraffers.collatime.exception.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlingController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    // 에러 페이지 정의
    private final String error404 = "/exception/error404page";
    private final String error403 = "/exception/error403page";
    private final String error500 = "/exception/error500page";
    private final String errorEtc = "/exception/etcerror";  // 만들 예정

    @RequestMapping(value = "/exception")
    public String handleError(HttpServletRequest request, Model model){
        // 에러 코드 획득
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            logger.error("httpStatus : " + statusCode);

            // 404 에러
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                model.addAttribute("errorname", "해당 페이지를 찾을 수 없습니다.");
//                model.addAttribute("code", status.toString());
//                model.addAttribute("msg", httpStatus.getReasonPhrase());
                return error404;
            }

            // 403 에러
            if(statusCode == HttpStatus.FORBIDDEN.value()){
                model.addAttribute("errorname", "해당 페이지에 대한 접근 권한이 없습니다.");
                return error403;
            }

            // 500 에러
            if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return error500;
            }

        }

        return errorEtc;
    }

}
