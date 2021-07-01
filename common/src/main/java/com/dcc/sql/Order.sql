-- auto Generated on 2021-07-01
-- DROP TABLE IF EXISTS `com.dcc.com.dcc.order`;
CREATE TABLE `com.dcc.com.dcc.order`
(
    id         BIGINT(15)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_at  DATETIME    NOT NULL COMMENT 'createAt',
    update_at  DATETIME    NOT NULL COMMENT 'updateAt',
    user_id    BIGINT(15)  NOT NULL COMMENT 'userId',
    username   VARCHAR(50) NOT NULL COMMENT 'username',
    goods_id   BIGINT(15)  NOT NULL COMMENT 'goodsId',
    goods_name VARCHAR(50) NOT NULL COMMENT 'goodsName',
    price      INT(11)     NOT NULL COMMENT 'price',
    `number`   INT(11)     NOT NULL COMMENT 'number',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'com.dcc.com.dcc.order';
