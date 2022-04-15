-- -------------------------------------------------------
-- -------------隔离级别：serializable 序列化-------------
-- -------------------------------------------------------

-- -----------比对示例 session1-----------
select @@global.transaction_isolation,@@transaction_isolation;

start transaction;
update user set money = 99 where username = 'zhangsan';
-- 先不提交
commit;



-- -----------serializable示例 session2-----------
-- 全局的mysql隔离级别，默认是 REPEATABLE-READ 
select @@global.transaction_isolation;

-- 当前会话的隔离级别
select @@transaction_isolation;

-- 设置为serializable，串行
set session transaction isolation level serializable;

start transaction;
update user set money = 100 where username = 'zhangsan'

commit;