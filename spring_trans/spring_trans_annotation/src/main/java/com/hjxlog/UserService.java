package com.hjxlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务传播性:解决了什么问题?
 * 事务传播行为是为了解决业务层方法之间互相调用的事务问题，当
 * 一个事务方法被另一个事务方法调用时，事务该以何种状态存在？
 * 例如新方法可能继续在现有事务中运行，也可能开启一个新事务，并在自己的事务中运行，等等，
 * 这些规则就涉及到事务的传播性。
 */
@Service
public class UserService {
    // 添加了依赖,已经自动交由spring容器管理
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService2 userService2;


    /**
     * 默认是require的:如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
     *
     * 传播性:REQUIRED  UserService有事务,userService2也有事务,执行的时候,将不会开启userService2的事务,有一个出错,就都回滚
     * 如果UserService没有事务，userService2有事务，那么出错的话，它自己回滚
     *
     * 没有异常的执行日志:
     * 开启一个事务,有传播性,隔离级别
     * Creating new transaction with name [com.hjxlog.UserService.transfer_required]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * Executing prepared SQL update
     * 执行语句
     * Executing prepared SQL statement [update user set money = ? where username=?;]
     * 加入当前已存在的事务中,也就是userService2.update_require();不会重新开启一个新事务,而是加入到当前事务中
     * Participating in existing transaction
     *
     * Executing prepared SQL update
     * Executing prepared SQL statement [update user set money = ? where username=?;]
     * 提交
     * Committing JDBC transaction
     * 释放JDBC连接
     * Releasing JDBC Connection
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer_required() {
        jdbcTemplate.update("update user set money = ? where username=?;", 1000, "zhangsan");
        userService2.update_require();
        /**
         * 出现异常的日志:
         * 前面的都一样
         * 事务回滚
         * Rolling back JDBC transaction
         * 释放jdbc连接
         * Releasing JDBC Connection
         */
        int i = 1 / 0;
    }

    /**
     * 默认是require的
     * <p>
     * 如果UserService没有事务，userService2有事务，那么出错的话，它自己回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer() {
        jdbcTemplate.update("update user set money = ? where username=?;", 1000, "zhangsan");
        userService2.update_require();
//        int i = 1 / 0;
    }

    @Transactional
    public void transfer1() {
        jdbcTemplate.update("update user set money = ? where username=?;", 1000, "zhangsan");
        userService2.update1();
//        int i = 1 / 0;
    }

    /**
     * 默认的传播级别 propagation = Propagation.REQUIRED
     * 一个事务调用另一个事务
     * 在主事务里面，出现异常，大家都会回滚；子事务里面出现异常，大家都会回滚
     */
    public void m1() {
        transfer();
    }

}
