CREATE TABLE `newsinfo` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `status` varchar(11) NOT NULL,
  `auth` varchar(255) CHARACTER SET armscii8 DEFAULT NULL,
  `show` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浏览权限';

