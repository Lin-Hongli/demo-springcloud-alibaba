SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `xxxxxxx`;
CREATE TABLE `xxxxxxx`  (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',

`xxx` varchar(255) DEFAULT NULL COMMENT '备注',
`xxx_state` int(11) DEFAULT '0' COMMENT 'xxx',
`xxx_time` datetime DEFAULT NULL COMMENT 'xxx',

`create_id` bigint NULL DEFAULT NULL COMMENT '创建人id',
`update_id` bigint NULL DEFAULT NULL COMMENT '更新人id',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
`deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除（0=否，1=是）',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;
