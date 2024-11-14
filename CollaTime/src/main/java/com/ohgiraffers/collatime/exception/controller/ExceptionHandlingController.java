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
    private final String error400 = "/exception/error400page";
    private final String errorEtc = "/exception/erroretcpage";  // 만들 예정

    @RequestMapping(value = "/exception")
    public String handleError(HttpServletRequest request, Model model){
        // 에러 코드 획득
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            logger.error("httpStatus : " + statusCode);

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                // 404 에러
                model.addAttribute("errorname", "해당 페이지를 찾을 수 없습니다.");
                return error404;

            } else if(statusCode == HttpStatus.FORBIDDEN.value()){
                // 403 에러
                model.addAttribute("errorname", "해당 페이지에 대한 접근 권한이 없습니다.");
                return error403;
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                // 500 에러
                return error500;
            } else if(statusCode == HttpStatus.BAD_REQUEST.value()){
                // 400 에러
                return error400;
            } else {
                // 그 외 기타 에러
                return errorEtc;
            }

        } else {
            // 그 외 기타 에러
            return errorEtc;
        }

    }

}
