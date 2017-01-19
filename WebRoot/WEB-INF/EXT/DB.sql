ALTER TABLE `es_interact_vote_option` 
	ADD COLUMN `linked` varchar(255) NULL AFTER `idx`,
	ADD COLUMN `linkurl` varchar(255) NULL AFTER `linked`;
	ALTER TABLE es_hy_user ADD INDEX IDX_WXUID_OWNER (WXUID,OWNER);
	ALTER TABLE es_feature_interact_apt_record ADD INDEX IDX_CREATETIME_ENTITYID (CREATETIME,ENTITYID);
	ALTER TABLE es_feature_interact_vote_record_option ADD INDEX IDX_UID_OPTIONID (UID,OPTIONID);
	ALTER TABLE es_feature_interact_vote_record_option ADD INDEX IDX_UI_OP_HY (UID,OPTIONID,HYDAY);
	ALTER TABLE `es_sms` 
	ADD COLUMN `hydesc` varchar(32) NULL AFTER `productid`;
	ALTER TABLE `es_wx_app` 
	ADD COLUMN `type` int(11) NOT NULL DEFAULT 0 COMMENT '数字越大权限越高' AFTER `updatetime`;
	
	CREATE TABLE `es_pay_apt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hyuid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	ALTER TABLE `es_pay_shopping_cart` 
	ADD COLUMN `paid` bigint NULL COMMENT 'pay_apt id' AFTER `usejf`;
	
	ALTER TABLE `es_pay_order` 
	ADD COLUMN `paid` bigint NULL COMMENT 'pay_apt id' AFTER `usejf`;
	
	ALTER TABLE `es_wx_mp` 
	ADD COLUMN `owner` bigint NULL AFTER `key`;
	
	ALTER TABLE `es_wx_page_show` 
	ADD COLUMN `mpid` bigint NULL AFTER `spageid`;
	
	ALTER TABLE `es_wx_mp` 
	ADD COLUMN `appsecret` varchar(32) NULL AFTER `owner`,
	ADD COLUMN `token` varchar(32) NULL AFTER `appsecret`,
	ADD COLUMN `encoding_key` varchar(32) NULL AFTER `token`,
	ADD COLUMN `access_token` varchar(32) NULL AFTER `encoding_key`,
	ADD COLUMN `expires_in` bigint NULL AFTER `access_token`;
	
	ALTER TABLE `es_wx_mp` 
	ADD KEY `ow`(`owner`);
	
	ALTER TABLE `es_wx_app` 
	ADD KEY `ty`(`type`);
	
	ALTER TABLE `es_wx_mp` 
	ADD COLUMN `user_get` char(1) NOT NULL DEFAULT 'N' COMMENT 'N-未获取关注者,Y-已获取关注者' AFTER `expires_in`;
	
	ALTER TABLE `es_wx_open_auth` 
	DROP COLUMN `user_get`;
	
	
	
