package com.hjxlog.annotation;

import com.hjxlog.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Huang JX
 * @Date: 2022/04/15
 * @Description: 注解方式
 */
public class UserDemo2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService2 userService2 = ctx.getBean(UserService2.class);
        userService2.transfer();
    }

}
