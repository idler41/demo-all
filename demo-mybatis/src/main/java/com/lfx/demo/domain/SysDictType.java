package com.lfx.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型表: t_sys_dict_type
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-01-31 21:14:07
 */
@Data
public class SysDictType implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String dictLabel;

    /**
     * 状态: 0-正常 1-停用）
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标记: 0-正常 1-删除）
     */
    private Integer deleteFlag;

    /**
     * 创建者
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
}