package com.hjxlog.spring_boot_base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBaseApplication {

    /**
     * 启动流程:
     * 1. run(new Class[]{primarySource}, args); ===> new一个SpringApplication对象
     * 2.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseApplication.class, args);
    }

}
