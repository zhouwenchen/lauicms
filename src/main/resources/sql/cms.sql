CREATE TABLE `newsInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `newsId` varchar(20) NOT NULL,
  `newsName` varchar(255) NOT NULL,
  `newsUrl` varchar(255) DEFAULT NULL,
  `newsSource` varchar(255) DEFAULT NULL,
  `newsAuthor` varchar(255) DEFAULT NULL,
  `newsStatus` varchar(11) NOT NULL,
  `newsLook` varchar(255) DEFAULT NULL,
  `isShow` varchar(255) DEFAULT NULL,
  `imgUrls` varchar(500) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  `newsContent` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='浏览权限';
