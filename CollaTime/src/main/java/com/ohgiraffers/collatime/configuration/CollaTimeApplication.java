package com.ohgiraffers.collatime.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ohgiraffers.collatime")
@MapperScan(basePackages = "com.ohgiraffers.collatime", annotationClass = Mapper.class)
public class CollaTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollaTimeApplication.class, args);
    }

}
