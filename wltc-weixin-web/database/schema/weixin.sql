-- ----------------------------
--  Table structure for `weixin_activity`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_activity`;
CREATE TABLE `weixin_activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `name` varchar(100) DEFAULT NULL COMMENT '活动名称',
  `type` char(1) DEFAULT NULL COMMENT '活动类型：0：现金优惠 1：折扣优惠 2：减免优惠',
  `begin_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `end_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '活动结束时间',
  `content` text COMMENT '活动内容',
  `org_id` int(11) DEFAULT NULL COMMENT '组织ID',
  `line_id` int(11) DEFAULT NULL COMMENT '专线ID',
  `status` char(1) DEFAULT NULL COMMENT '优惠活动状态：0：提交未审核 1：审核通过未发布 2：活动进行中 3：活动已结束',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `source` int(1) DEFAULT '0',
  `customer_count` int(11) DEFAULT NULL COMMENT '活动参与人数',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='优惠活动';

-- ----------------------------
--  Table structure for `weixin_activity_auth`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_activity_auth`;
CREATE TABLE `weixin_activity_auth` (
  `activity_auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠活动审核ID',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `line_id` int(11) DEFAULT NULL COMMENT '专线ID',
  `org_id` int(11) DEFAULT NULL COMMENT '组织ID',
  `apply_man` varchar(10) DEFAULT NULL COMMENT '申请人',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` varchar(200) DEFAULT NULL COMMENT '申请备注',
  `audit_man` varchar(10) DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_note` varchar(200) DEFAULT NULL COMMENT '审核意见',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `source` char(1) DEFAULT NULL COMMENT '来源： 0：平台 1：微信 2：App',
  PRIMARY KEY (`activity_auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动审核记录表';

-- ----------------------------
--  Table structure for `weixin_collected_line`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_collected_line`;
CREATE TABLE `weixin_collected_line` (
  `customer_id` int(11) NOT NULL COMMENT '客户ID',
  `line_id` int(11) NOT NULL DEFAULT '0' COMMENT '专线ID',
  `collect_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`customer_id`,`line_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户收藏的专线';

-- ----------------------------
--  Table structure for `weixin_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_coupon`;
CREATE TABLE `weixin_coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券ID',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `coupon_code` varchar(32) DEFAULT NULL COMMENT '优惠券代码',
  `get_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '优惠券领取时间',
  `consume_time` datetime DEFAULT NULL COMMENT '优惠券消费时间',
  `line_deal_status` char(1) DEFAULT '0' COMMENT '专线处理状态：0：待处理；1：已处理',
  `line_deal_comment` varchar(255) DEFAULT NULL COMMENT '专线处理意见',
  `line_deal_time` datetime DEFAULT NULL COMMENT '专线处理时间',
  `line_deal_feedback` varchar(100) DEFAULT NULL COMMENT '专线反馈意见： 比如 - 专线已确认，已获取优惠资格',
  `status` char(1) DEFAULT NULL COMMENT '优惠券处理状态：0：无效；1：有效',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户优惠券';

-- ----------------------------
--  Table structure for `weixin_coupon_order`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_coupon_order`;
CREATE TABLE `weixin_coupon_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '预订单ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `line_id` int(11) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `customer_orgname` varchar(100) DEFAULT NULL COMMENT '客户公司名',
  `cargo_name` varchar(100) DEFAULT NULL COMMENT '货物名称',
  `cargo_number` int(10) DEFAULT NULL COMMENT '货物数量',
  `cargo_unit` char(1) DEFAULT NULL COMMENT '货物单位',
  `destination` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `weixin_customer`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_customer`;
CREATE TABLE `weixin_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信客户ID',
  `user_info_id` int(11) DEFAULT NULL COMMENT '专线平台用户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `mobile` varchar(30) DEFAULT NULL COMMENT '客户手机号码',
  `weixin` varchar(30) DEFAULT NULL COMMENT '微信号',
  `openid` varchar(50) DEFAULT NULL COMMENT '微信OpenID',
  `subscribe_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  `unsubscribe_time` datetime DEFAULT NULL COMMENT '取消关注时间',
  `status` char(255) DEFAULT NULL COMMENT '客户状态 0:未关注 1:关注',
  `last_active_time` datetime DEFAULT NULL COMMENT '最后一次活动时间',
  `customer_type` varchar(10) DEFAULT NULL COMMENT '客户类型 0:普通客户 1:专线客户',
  `line_id` int(11) DEFAULT NULL COMMENT '专线ID',
  `org_id` int(11) DEFAULT NULL COMMENT '专线公司ID',
  `is_bind` char(1) DEFAULT NULL COMMENT '是否绑定',
  `verify_code` varchar(8) DEFAULT NULL COMMENT '验证码',
  `verify_time` datetime DEFAULT NULL COMMENT '验证时间',
  `nickname` varchar(50) DEFAULT NULL COMMENT '微信用户昵称',
  `sex` varchar(1) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `province` varchar(50) DEFAULT NULL COMMENT '用户个人资料填写的省份',
  `city` varchar(50) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `country` varchar(50) DEFAULT NULL COMMENT '国家，如中国为CN',
  `headimgurl` varchar(1000) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空',
  `isoauth` varchar(1) DEFAULT NULL COMMENT '是否获取过微信用户信息',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `index_openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- ----------------------------
--  Table structure for `weixin_news`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_news`;
CREATE TABLE `weixin_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻ID',
  `news_url` varchar(255) DEFAULT NULL COMMENT '该新闻的URL',
  `is_main_news` char(1) NOT NULL DEFAULT '' COMMENT '是否为头条新闻 - 一个新闻组有一个头条新闻',
  `section` char(1) DEFAULT NULL COMMENT '栏目 0:新闻 1:优惠新闻',
  `title` varchar(50) DEFAULT NULL COMMENT '新闻标题',
  `description` varchar(200) DEFAULT NULL COMMENT '新闻摘要',
  `image_url` varchar(100) DEFAULT NULL COMMENT '新闻图片链接',
  `content` text COMMENT '新闻正文内容',
  `status` char(1) DEFAULT NULL COMMENT '发布状态',
  `update_time` datetime DEFAULT NULL COMMENT '新闻更新时间',
  PRIMARY KEY (`news_id`,`is_main_news`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻内容';

-- ----------------------------
--  Table structure for `weixin_news_group`
-- ----------------------------
DROP TABLE IF EXISTS `weixin_news_group`;
CREATE TABLE `weixin_news_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '新闻组标题',
  `section` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '新闻栏目 0:新闻 1:优惠新闻',
  `news_ids` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '新闻ID组合 1|2|3|4|7|9',
  `status` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否发布1：发布 0：停止 一种新闻只能有一个是发布状态',
  `update_time` datetime DEFAULT NULL COMMENT '发布时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `wz_wzfjb`
-- ----------------------------
DROP TABLE IF EXISTS `wz_wzfjb`;
CREATE TABLE `wz_wzfjb` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fjmc` varchar(200) DEFAULT NULL COMMENT '附件名称',
  `fjlj` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `scsj` datetime DEFAULT NULL COMMENT '上传时间',
  `scr` varchar(20) DEFAULT NULL COMMENT '上传人',
  `sfsc` char(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站附件表（存贮照片、文件等信息）';

SET FOREIGN_KEY_CHECKS = 1;