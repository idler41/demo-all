package com.lfx.demo.abstracts.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-04-01 15:04:38
 */
@Data
public class SysRoleExcel implements Serializable {

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
}