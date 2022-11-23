package com.lfx.demo.domain;

import java.time.LocalDateTime;

/**
 * @author linfuxin Created on 2022-02-14 11:23:27
 */
public interface BaseDomain {

    /**
     * 获取创建人
     *
     * @return 创建人
     */
    public Integer getCreateBy();

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy);

    /**
     * 获取创建人名称
     *
     * @return 创建人名称
     */
    public String getCreateName();

    /**
     * 设置创建人名称
     *
     * @param createName 创建人名称
     */
    public void setCreateName(String createName);

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public LocalDateTime getCreateTime();

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(LocalDateTime createTime);

    /**
     * 获取更新人
     *
     * @return 更新人
     */
    public Integer getUpdateBy();

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy);

    /**
     * 获取更新人名称
     *
     * @return 更新人名称
     */
    public String getUpdateName();

    /**
     * 获取更新人名称
     *
     * @param createName 更新人名称
     */
    public void setUpdateName(String createName);

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    public LocalDateTime getUpdateTime();

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime);
}
