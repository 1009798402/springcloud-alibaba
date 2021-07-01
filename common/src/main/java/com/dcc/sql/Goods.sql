-- auto Generated on 2021-07-01
-- DROP TABLE IF EXISTS goods;
CREATE TABLE goods(
	id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	create_at DATETIME NOT NULL COMMENT 'createAt',
	update_at DATETIME NOT NULL COMMENT 'updateAt',
	goods_name VARCHAR (50) NOT NULL COMMENT 'goodsName',
	price INT (11) NOT NULL COMMENT 'price',
	stock INT (11) NOT NULL COMMENT 'stock',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'goods';
