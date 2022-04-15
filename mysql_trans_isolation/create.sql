-- 建表
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `money` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `trans_test`.`user`(`id`, `username`, `money`) VALUES (1, 'zhangsan', 1);
INSERT INTO `trans_test`.`user`(`id`, `username`, `money`) VALUES (2, 'lisi', 1);