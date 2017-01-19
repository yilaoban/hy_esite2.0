ALTER TABLE `es_pay_jf_shop` 
	ADD COLUMN `lpage` bigint NULL AFTER `fpage`;

ALTER TABLE `es_pay_shop` 
	ADD COLUMN `lpage` bigint NULL AFTER `fpage`;