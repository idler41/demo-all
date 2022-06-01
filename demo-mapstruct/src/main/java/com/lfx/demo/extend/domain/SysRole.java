package com.lfx.demo.extend.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表: t_sys_role
 *
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-04-01 15:04:38
 */
@Data
public class SysRole implements Serializable {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色标识
     */
    private String code;

    /**
     * 角色标签
     */
    private String label;

    /**
     * 超级管理员标记: 0-否 1-是
     */
    private Integer adminFlag;

    /**
     * 角色状态: 0-正常 1-禁用
     */
    private Integer state;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}