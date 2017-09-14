package com.sh.pri.pojo;


/**--  
 * 	-- 用户中心表  
 * 	--  
 *    
 * 	CREATE TABLE IF NOT EXISTS `t_user_info` (  
 *   		`id` int(10) unsigned NOT NULL COMMENT '用户ID',  
 *   		`username` char(16) NOT NULL COMMENT '用户名',  
 *  		`password` char(32) NOT NULL COMMENT '密码',  
 *   		`email` char(32) NOT NULL COMMENT '用户邮箱',  
 *   		`mobile` char(15) NOT NULL COMMENT '用户手机',  
 *   		`reg_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '注册时间',  
 *   		`reg_ip` bigint(20) NOT NULL DEFAULT '0' COMMENT '注册IP',  
 *   		`last_login_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最后登录时间',  
 *   		`last_login_ip` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后登录IP',  
 *   		`update_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',  
 *   		`status` tinyint(4) DEFAULT '0' COMMENT '用户状态'  
 * 		) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户表' AUTO_INCREMENT=1 ;  
 * 
 */
public class TUserInfo {

}
