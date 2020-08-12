package com.lfx.demo.converter;

import com.lfx.demo.entity.ActTaskGroup;
import com.lfx.demo.controller.vo.ActTaskGroupVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-30 16:37:10
 */
@Mapper
public interface ActTaskGroupConverter {

        ActTaskGroupConverter INSTANCE = Mappers.getMapper(ActTaskGroupConverter.class);

        ActTaskGroupVo doToVo(ActTaskGroup domain);

        List<ActTaskGroupVo> doListToVoList(List<ActTaskGroup> list);
}