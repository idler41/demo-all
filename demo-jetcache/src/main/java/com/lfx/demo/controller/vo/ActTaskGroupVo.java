package com.lfx.demo.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-30 16:23:49
 */
@Data
public class ActTaskGroupVo implements Serializable {

    /**
     * 任务组id
     */
    private Integer id;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 任务组名称
     */
    private String groupName;

    /**
     * 任务组类型
     */
    private Integer groupType;

    /**
     * 任务组奖励类型
     */
    private Integer rewardType;

    /**
     * 任务组奖励数量
     */
    private Integer rewardValue;

    /**
     * 扩展json
     */
    private String extendJson;

    /**
     * 当前状态 0--停用  1--启用
     */
    private Integer groupStatus;

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

    /**
     * 应用编码
     */
    private String appOwnerCode;

    /**
     * 社团ID (工具商ID)
     */
    private Integer clubId;

    /**
     * 应用ID
     */
    private String appId;
}