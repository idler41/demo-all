package com.lfx.demo.controller;

import com.lfx.demo.domain.SysDictType;
import com.lfx.demo.manager.SysDictTypeManager;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a> created on 2022-01-31 21:14:07
 */
@Api(value = "字典类型api", tags = "字典类型接口")
@RestController
@RequestMapping("/sysDictType")
@Slf4j
@Validated
public class SysDictTypeController {

    @Autowired
    private SysDictTypeManager manager;

    @GetMapping({"/get"})
    public SysDictType get(@Validated @NotNull @RequestParam Integer id) {
        return this.manager.get(id);
    }
}