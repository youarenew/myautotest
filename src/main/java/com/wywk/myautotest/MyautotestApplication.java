package com.wywk.myautotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.wywk.myautotest.mapper")
public class MyautotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyautotestApplication.class, args);
    }
}
