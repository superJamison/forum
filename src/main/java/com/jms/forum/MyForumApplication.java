package com.jms.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jms.forum.mapper")
public class MyForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyForumApplication.class, args);
    }

}
