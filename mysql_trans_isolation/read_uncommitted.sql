-- session 1
-- -----------比对示例-----------
select @@global.transaction_isolation,@@transaction_isolation;

start transaction;
update user set money = 102 where username = 'zhangsan';

ROLLBACK;

commit;



-- session 2
-- -------------隔离级别：read uncommitted 未提交读-------------

-- 全局的mysql隔离级别，默认是 REPEATABLE-READ 
select @@global.transaction_isolation;

select @@transaction_isolation;

set session transaction isolation level read uncommitted;

start transaction;
-- 其他事务还没提交，看不到数据
select * FROM user where username = 'zhangsan';
-- 先不提交
commit;

-- 总结：会有幻读，脏读，不可重复读