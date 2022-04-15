-- -------------隔离级别：read committed 提交读-------------

-- session 1
-- 全局的mysql隔离级别，默认是 REPEATABLE-READ 
select @@global.transaction_isolation;

select @@transaction_isolation;

set session transaction isolation level read committed;

start transaction;

select * FROM user where username = 'zhangsan';
-- 先不提交
commit;

-- session 2
-- -----------比对示例-----------
select @@global.transaction_isolation,@@transaction_isolation;

start transaction;
update user set money = 102 where username = 'zhangsan';
commit;

-- 总结：可以读取到别人提交的数据，造成不可重复读的问题，幻读问题表现在：比如一开始查到1条数据，别的事务提交了10条数据，之前的事务发现可以读到11条