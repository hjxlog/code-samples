package com.hjxlog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.hjxlog.annotation") // 扫描包
//开启事务的注解支持
@EnableTransactionManagement
public class JavaConfig {

    /**
     * @Configuration 类似xml配置的方式,创建装配bean
     */

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("123456");
        ds.setUrl("jdbc:mysql:///spring_tran?serverTimezone=Asia/Shanghai");
        return ds;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        // 注入
        return new JdbcTemplate(dataSource());
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
