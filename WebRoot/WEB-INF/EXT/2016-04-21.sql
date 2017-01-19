ALTER TABLE `es_hy_user_balance_set` 
	MODIFY COLUMN `sclicknum` int(11) NOT NULL DEFAULT 0 COMMENT '分享带来的点击人数，24小时有效' AFTER `ssharenum`;
	