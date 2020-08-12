package com.lfx.demo.converter;

import com.lfx.demo.entity.ActTask;
import com.lfx.demo.controller.vo.ActTaskVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-30 16:37:10
 */
@Mapper
public interface ActTaskConverter {

        ActTaskConverter INSTANCE = Mappers.getMapper(ActTaskConverter.class);

        @Mappings({
                @Mapping(source="createTime", target="createTime",dateFormat="yyyy-MM-dd HH:mm:ss")
        })
        ActTaskVo doToVo(ActTask domain);

        List<ActTaskVo> doListToVoList(List<ActTask> list);
}