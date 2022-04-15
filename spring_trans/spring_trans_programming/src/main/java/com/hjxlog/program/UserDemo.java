package com.hjxlog.program;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Huang JX
 * @Date: 2022/04/15
 * @Description: 通过xml配置方式
 */
public class UserDemo {

    public static void main(String[] args) {
        // 根据配置文件获取上下文,有配置扫描包
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        // 获取bean
        UserService userService = ctx.getBean(UserService.class);
//        userService.transferByJdbc();
//        userService.transferByTransactionTemplate();
        userService.transferByTransactional();
    }

}
