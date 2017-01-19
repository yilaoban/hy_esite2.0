ALTER TABLE `es_yu_yue_record` 
	ADD COLUMN `tag1` varchar(32) NULL AFTER `catname`,
	ADD COLUMN `tag2` varchar(32) NULL AFTER `tag1`;
