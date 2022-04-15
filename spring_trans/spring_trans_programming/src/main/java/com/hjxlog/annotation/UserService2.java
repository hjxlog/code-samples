package com.hjxlog.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 当@Transactional 注解加在类上面的时候，表示该类的所有方法都有事务，该
 * 注解加在方法上面的时候，表示该方法有事务。
 */
@Component
public class UserService2 {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transfer() {
        jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
//        int i = 1 / 0;
    }

}
