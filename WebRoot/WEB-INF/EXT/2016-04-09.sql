ALTER TABLE `es_pay_shop` 
	ADD COLUMN `bili` int(11) NULL DEFAULT 100 AFTER `fpage`;
ALTER TABLE `es_pay_jf_shop` 
	ADD COLUMN `bili` int(11) NULL DEFAULT 100 AFTER `fpage`;
	
DROP TABLE IF EXISTS `es_pay_order` ;
CREATE TABLE `es_pay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hyuid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `ownerid` bigint(20) DEFAULT NULL,
  `createtime` datetime NOT NULL COMMENT '下单时间',
  `addressid` bigint(20) DEFAULT NULL COMMENT '物流地址',
  `sendtime` datetime DEFAULT NULL COMMENT '货发时间',
  `totalprice` int(11) DEFAULT '0' COMMENT '商品总价',
  `discountprice` int(11) DEFAULT '0' COMMENT '抵消价格',
  `realprice` int(11) DEFAULT '0' COMMENT '成交价格',
  `expressid` varchar(255) DEFAULT NULL COMMENT '快递单号',
  `del_tag` char(1) DEFAULT 'N' COMMENT '是否取消订单',
  `status` char(3) DEFAULT 'DFK' COMMENT 'DFK-待付款;DQR:待确定DFH-待付款;DSH-待收货;CMP-已完成;YQX:取消订单',
  `ip` varchar(255) DEFAULT NULL,
  `subtype` int(11) DEFAULT '0' COMMENT '0-积分城商 1-微商城',
  `fatherorderid` bigint(20) DEFAULT NULL,
  `usejf` int(11) DEFAULT NULL,
  `goodscount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `hyuid` (`hyuid`),
  KEY `addressid` (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;


CREATE TABLE `es_pay_order_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) NOT NULL COMMENT '订单id',
  `productid` bigint(20) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `productsubtype` char(1) DEFAULT NULL COMMENT 'S-实物；K-卡券',
  `productsimg` varchar(255) DEFAULT NULL,
  `pcodeid` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '时当产品单价',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0-积分，1-人民币',
  `uuid` char(10) DEFAULT NULL,
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `paid` bigint(20) DEFAULT NULL COMMENT 'pay_apt id 个性化',
  `used` char(1) DEFAULT 'N' COMMENT '是否使用过',
  `shoppingcartid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `es_pay_order_goods_ibfk_2` (`orderid`),
  KEY `productid` (`productid`),
  CONSTRAINT `es_pay_order_goods_ibfk_2` FOREIGN KEY (`orderid`) REFERENCES `es_pay_order` (`id`),
  CONSTRAINT `es_pay_order_goods_ibfk_3` FOREIGN KEY (`productid`) REFERENCES `es_content_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

DELIMITER ;;
CREATE TRIGGER `goodscount` AFTER INSERT ON `es_pay_order_goods` FOR EACH ROW update es_pay_order set goodscount=goodscount+1 where id = new.orderid;;
DELIMITER ;



ALTER TABLE `es_pay_record` 
	ADD COLUMN `hyuid` bigint NULL AFTER `price`,
	ADD COLUMN `orderid` bigint NULL AFTER `hyuid`;
	
	

ALTER TABLE `es_pay_address` 
	ADD COLUMN `isdefault` char(1) NULL DEFAULT 'N' COMMENT '是否是默认地址 Y是 N否' AFTER `telphone`;
