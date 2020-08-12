package com.lfx.demo.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfx.demo.entity.ActTask;
import com.lfx.demo.entity.ActTaskGroup;
import com.lfx.demo.mapper.ActTaskGroupMapper;
import com.lfx.demo.mapper.ActTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-16 18:54:04
 */
@Service
public class ActTaskGroupManager extends ServiceImpl<ActTaskGroupMapper, ActTaskGroup> {

    @Autowired
    private ActTaskMapper actTaskMapper;

    @Override
    public ActTaskGroup getById(Serializable id) {
        return super.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(ActTaskGroup entity) {
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGroupAndTaskList(ActTaskGroup actTaskGroup, List<ActTask> actTaskList) {
        baseMapper.updateById(actTaskGroup);
        for (ActTask item : actTaskList) {
            actTaskMapper.updateById(item);
        }
    }
}