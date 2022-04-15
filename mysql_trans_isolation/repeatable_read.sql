-- -------------隔离级别：repeatable read 可重复读-------------

-- 全局的mysql隔离级别，默认是 REPEATABLE-READ 
select @@global.transaction_isolation;

-- 当前会话的隔离级别
select @@transaction_isolation;

-- 设置为repeatable read，可重复读
set session transaction isolation level repeatable read;

start transaction;
-- 其他事务还没提交，看不到数据
select * FROM user where username = 'zhangsan';
-- 先不提交
commit;


-- -----------比对示例-----------
select @@global.transaction_isolation,@@transaction_isolation;

start transaction;
update user set money = 100 where username = 'zhangsan';
commit;

-- 总结：看不到别人未提交的数据，如果自己先开了事务，也看不到后面的提交事务，可以达到可重复读的目的，比如用在一些报表中。


-- 幻读问题
-- session1
start transaction;
select * FROM user where username = 'wangwu';
insert into user(id,username,money) VALUES (100,'wangwu',20);
commit;


-- session2
start transaction;
select * FROM user where username = 'wangwu';
-- 明明没有，但是插不进去，唯一性冲突了，这也是幻读的一种表现方式
insert into user(id,username,money) VALUES (100,'wangwu',20);

commit;