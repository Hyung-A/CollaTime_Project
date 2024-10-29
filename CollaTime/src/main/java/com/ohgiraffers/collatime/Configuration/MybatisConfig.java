package com.ohgiraffers.collatime.Configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ohgiraffers.collatime", annotationClass = Mapper.class)
public class MybatisConfig {



}
