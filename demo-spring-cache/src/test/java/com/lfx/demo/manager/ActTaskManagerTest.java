package com.lfx.demo.manager;

import com.lfx.demo.AbstractSpringTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-17 13:47:50
 */
public class ActTaskManagerTest extends AbstractSpringTest {

    @Autowired
    private ActTaskManager actTaskManager;


    @BeforeClass
    public static void init() {
        initSysProperty();
    }

    @Test
    public void getById() {
        System.out.println(toJsonStr(actTaskManager.getById(1), true));
        System.out.println(toJsonStr(actTaskManager.getById(1), true));
        System.out.println(toJsonStr(actTaskManager.getById(1), true));
    }

}
