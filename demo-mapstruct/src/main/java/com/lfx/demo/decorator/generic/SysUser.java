package com.lfx.demo.decorator.generic;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户表: sys_user
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-11-07 10:48:41
 */
@Data
public class SysUser implements BaseDomain, Serializable {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 性别: 0-男 1-女
     */
    private Integer gender;

    /**
     * 用户状态: 0-冻结 1-正常
     */
    private Integer state;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 创建者id
     */
    private Integer createBy;

    /**
     * 创建者
     */
    private String createName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者id
     */
    private Integer updateBy;

    /**
     * 更新者
     */
    private String updateName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}