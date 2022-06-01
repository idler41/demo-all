package com.lfx.demo.abstracts.saas.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户-角色关联表: sys_user_role
 *
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-05-01 10:06:14
 */
@Data
public class SysUserRole implements SaasDomain, Serializable {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}