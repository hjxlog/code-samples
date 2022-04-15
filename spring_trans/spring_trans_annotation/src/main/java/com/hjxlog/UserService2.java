package com.hjxlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService2 {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.REQUIRED)
    public void update_require() {
        jdbcTemplate.update("update user set money = ? where username=?;", 1000, "lisi");
//        int i = 1 / 0;
    }

    //    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update1() {
        jdbcTemplate.update("update user set money = ? where username=?;", 1000, "lisi");
//        int i = 1 / 0;
        /**
         * 如果
         */
    }
}
