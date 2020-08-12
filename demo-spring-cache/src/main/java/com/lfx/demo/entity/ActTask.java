package com.lfx.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-16 18:54:04
 */
@TableName("t_act_task")
@Data
public class ActTask implements Serializable {

    /**
     * 任务id
     */
    private Integer id;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 任务组id
     */
    private Integer groupId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 任务完成目标值
     */
    private Integer targetValue;

    /**
     * 重复执行 0--单次执行 1--重复执行
     */
    private Integer repeatFlag;

    /**
     * 任务组奖励类型
     */
    private Integer rewardType;

    /**
     * 奖励值
     */
    private String rewardValue;

    /**
     * 任务排序
     */
    private Integer taskOrder;

    /**
     * 当前状态 0--停用  1--启用
     */
    private Integer taskStatus;

    /**
     * 逻辑删除 0-否 1-是
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}