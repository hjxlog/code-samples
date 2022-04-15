package com.hjxlog.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: Huang JX
 * @Date: 2022/04/15
 * @Description: UserService 需要@Component注解放入容器,不然找不到:No qualifying bean of type 'com.hjxlog.program.UserService' available
 */
@Component
public class UserService {

    /**
     * public interface PlatformTransactionManager extends TransactionManager {
     * // 根据TransactionDefinition定义一个事务，TransactionStatus可以看作就是事务
     * // TransactionDefinition 中定义了一些事务的基本规则，例如传播性、隔离级别等。
     * TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
     * // 提交事务
     * void commit(TransactionStatus status) throws TransactionException;
     * // 回滚事务
     * void rollback(TransactionStatus status) throws TransactionException;
     * }
     * 定义了基本的事务操作方法，这些事务操作方法都是平台无关的，具体的实现都是由不同的子类来实现的。
     * 它有众多实现：
     * 如果你使用的是 JDBC 那么可以将 DataSourceTransactionManager 作为事务管理器；
     * 如果你使用的是 Hibernate，那么可以将 HibernateTransactionManager 作为事务管理器；
     * 如果你使用的是 JPA，那么可以将 JpaTransactionManager 作为事务管理器。
     */

    /**
     * @Autowired 按照类型注入
     */
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;


    /**
     * 使用jdbcTemplate
     */
    public void transferByJdbcTemplate() {
        /**
         * spring 事务关键组件:
         * PlatformTransactionManager
         * TransactionDefinition
         * TransactionStatus
         */

        // 可以定义事务 隔离级别,传播性,是否只读,超时时间
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setTimeout(3000);
        // 根据 DefaultTransactionDefinition 获取事务对象 TransactionStatus
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            // jdbcTemplate 执行语句
            jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
//            int i = 1 / 0;
            //提交事务
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            e.printStackTrace();
            //回滚事务
            transactionManager.rollback(status);
        }
    }

    public void transferByTransactionTemplate() {
        //配置隔离性
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
        transactionTemplate.setReadOnly(true);
        transactionTemplate.setTimeout(3000);

        // 需要返回结果的话,用TransactionCallback
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
//                    int i = 1 / 0;
                } catch (DataAccessException e) {
                    //设置当前事务回滚
                    status.setRollbackOnly();
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 通过AOP的方式开启事务
     */
    @Transactional
    public void transferByTransactional() {
        jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
//        int i = 1 / 0;
    }

}
