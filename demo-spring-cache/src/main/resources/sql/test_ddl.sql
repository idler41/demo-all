CREATE TABLE `t_act_task_group` (
`id` int(11) NOT NULL  AUTO_INCREMENT COMMENT '任务组id',
`act_id` int(11) NOT NULL  COMMENT '活动id',
`group_name` varchar(64) NOT NULL  COMMENT '任务组名称',
`group_type` smallint(6) NOT NULL DEFAULT 0 COMMENT '任务组类型',
`reward_type` smallint(6) NOT NULL DEFAULT 0 COMMENT '任务组奖励类型',
`reward_value` int(11) NOT NULL DEFAULT 0 COMMENT '任务组奖励数量',
`extend_json` varchar(64) NOT NULL DEFAULT 0   COMMENT '扩展json',
`group_status` tinyint(1) NOT NULL DEFAULT 0  COMMENT '当前状态 0--停用  1--启用',
`delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '任务组表';

CREATE TABLE `t_act_task` (
`id` int(11) NOT NULL  AUTO_INCREMENT COMMENT '任务id',
`act_id` int(11) NOT NULL  COMMENT '活动id',
`group_id` int(11) NOT NULL  COMMENT '任务组id',
`task_name` varchar(64) NOT NULL  COMMENT '任务名称',
`task_type` smallint(6) NOT NULL  COMMENT '任务类型',
`target_value` int(11) NOT NULL  COMMENT '任务完成目标值',
`repeat_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '重复执行 0--单次执行 1--重复执行',
`reward_type` smallint(6) NOT NULL  COMMENT '任务组奖励类型',
`reward_value` varchar(64) NOT NULL  COMMENT '奖励值',
`task_order` int(11) NOT NULL DEFAULT 0  COMMENT '任务排序',
`task_status` tinyint(1) NOT NULL DEFAULT 0  COMMENT '当前状态 0--停用  1--启用',
`delete_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-否 1-是',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '任务表';