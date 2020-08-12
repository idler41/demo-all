package com.lfx.demo.manager;

import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.lfx.demo.AbstractSpringTest;
import com.lfx.demo.converter.ActTaskConverter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-17 13:47:50
 */
public class ActTaskManagerTest extends AbstractSpringTest {

    @Autowired
    private ActTaskManager manager;

    @Autowired
    private GlobalCacheConfig globalCacheConfig;

    @BeforeClass
    public static void init() {
        initSysProperty();
    }

    @Test
    public void getById() throws InterruptedException {
        System.out.println(toJsonStr(manager.getById(1), true));
        Thread.sleep(3000);
        System.out.println(toJsonStr(manager.getById(1), true));
        Thread.sleep(3000);
        System.out.println(toJsonStr(manager.getById(1), true));
    }

    @Test
    public void selectByIds() throws InterruptedException {
        System.out.println(manager.selectByIds(Arrays.asList(1, 2, 3)));
        Thread.sleep(1000);
        System.out.println(manager.selectByIds(Arrays.asList(1, 2, 3)));
        Thread.sleep(1000);
        System.out.println(manager.selectByIds(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void mapStruct() {
        System.out.println(toJsonStr(ActTaskConverter.INSTANCE.doToVo(manager.getById(1)), true));
    }

}
