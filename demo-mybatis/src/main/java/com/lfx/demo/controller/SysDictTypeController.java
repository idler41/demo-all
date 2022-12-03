package com.lfx.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
import java.util.List;

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

    @GetMapping({"/get0"})
    public SysDictType get0(@Validated @NotNull @RequestParam Integer id) {
        return this.manager.get(id);
    }

    @GetMapping({"/get"})
    public SysDictType get(@Validated @NotNull @RequestParam Integer id) {
        return this.manager.selectById(id);
    }

    @GetMapping({"/get1"})
    public SysDictType get1(@Validated @NotNull @RequestParam String label) {
        return this.manager.getByDictLabel(label);
    }

    @GetMapping({"/get2"})
    public SysDictType get2(SysDictType sysDictType) {
        return this.manager.getByDictLabel(sysDictType);
    }

    @GetMapping({"/get3"})
    public List<SysDictType> get3(SysDictType sysDictType) {
        Page<SysDictType> page = PageHelper.startPage(1, 100, true).doSelectPage(() -> this.manager.getByDictLabel(sysDictType));
        return page.getResult();
    }

    @GetMapping({"/get4"})
    public SysDictType get4(@Validated @NotNull String[] labels) {
        return this.manager.getByDictLabelCollection(labels);
    }

    @GetMapping({"/get5"})
    public SysDictType get5() {
        return this.manager.select5();
    }

    @GetMapping({"/get6"})
    public SysDictType get6(Integer id) {
        return this.manager.select6(id);
    }
}