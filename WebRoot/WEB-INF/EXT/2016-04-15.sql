ALTER TABLE `es_yu_yue_servicer` 
	ADD COLUMN `oidx` int(11) NOT NULL DEFAULT 0 AFTER `type`,
	ADD COLUMN `otop` int(11) NULL AFTER `oidx`,
	ADD COLUMN `owner` bigint NULL AFTER `otop`,
	ADD COLUMN `leveltotal` int(11) NOT NULL DEFAULT 0 COMMENT '评价的等级总数' AFTER `owner`,
	ADD COLUMN `pjtotal` int(11) NOT NULL DEFAULT 0 COMMENT '评价的次数' AFTER `leveltotal`;
CREATE TABLE `es_yu_yue_servicer_pj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `srid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `dzcontent` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `es_off_chenck_dz_way` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `csid` bigint(20) DEFAULT NULL COMMENT 'check_source',
  `ceatetime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已经使用',
  `cwxuid` bigint(20) DEFAULT NULL COMMENT '二维码创建者',
  `swxuid` bigint(20) DEFAULT NULL COMMENT '描扫者',
  `stime` datetime DEFAULT NULL COMMENT '扫描时间',
  `rmb` int(11) NOT NULL DEFAULT '0' COMMENT '消费的rmb，单位分',
  `jf` int(11) NOT NULL COMMENT '获得的积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `es_off_check_source` 
	ADD COLUMN `type` int(11) NOT NULL DEFAULT 0 COMMENT '0-普通签到；1-关联消费积分' AFTER `gzeid`,
	ADD COLUMN `dzpageid` bigint NULL COMMENT '收营员页面' AFTER `type`,
	ADD COLUMN `dtpageid` bigint NULL COMMENT '生成动态二维码的页面' AFTER `dzpageid`,
	ADD COLUMN `jfpageid` bigint NULL COMMENT '消费获得积分成功的页面' AFTER `dtpageid`,
	ADD COLUMN `rmbjf` int(11) NOT NULL DEFAULT 0 COMMENT '消耗多少元获得一个积分' AFTER `jfpageid`;
ALTER TABLE `es_yu_yue_servicer` 
	ADD COLUMN `oidx` int(11) NOT NULL DEFAULT 0 AFTER `type`,
	ADD COLUMN `otop` int(11) NULL AFTER `oidx`,
	ADD COLUMN `owner` bigint NULL AFTER `otop`,
	ADD COLUMN `leveltotal` int(11) NOT NULL DEFAULT 0 COMMENT '评价的等级总数' AFTER `owner`,
	ADD COLUMN `pjtotal` int(11) NOT NULL DEFAULT 0 COMMENT '评价的次数' AFTER `leveltotal`;
CREATE TABLE `es_yu_yue_servicer_pj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `srid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `dzcontent` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `es_off_chenck_dz_way` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `csid` bigint(20) DEFAULT NULL COMMENT 'check_source',
  `ceatetime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已经使用',
  `cwxuid` bigint(20) DEFAULT NULL COMMENT '二维码创建者',
  `swxuid` bigint(20) DEFAULT NULL COMMENT '描扫者',
  `stime` datetime DEFAULT NULL COMMENT '扫描时间',
  `rmb` int(11) NOT NULL DEFAULT '0' COMMENT '消费的rmb，单位分',
  `jf` int(11) NOT NULL COMMENT '获得的积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `es_off_check_source` 
	ADD COLUMN `type` int(11) NOT NULL DEFAULT 0 COMMENT '0-普通签到；1-关联消费积分' AFTER `gzeid`,
	ADD COLUMN `dzpageid` bigint NULL COMMENT '收营员页面' AFTER `type`,
	ADD COLUMN `dtpageid` bigint NULL COMMENT '生成动态二维码的页面' AFTER `dzpageid`,
	ADD COLUMN `jfpageid` bigint NULL COMMENT '消费获得积分成功的页面' AFTER `dtpageid`,
	ADD COLUMN `rmbjf` int(11) NOT NULL DEFAULT 0 COMMENT '消耗多少元获得一个积分' AFTER `jfpageid`;

