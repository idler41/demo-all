package com.lfx.demo.manager;


import com.lfx.demo.AbstractSpringTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:idler41@163.con">idler41</a>
 * @date 2020-12-31 15:11:49
 */
public class ActivityManagerTest extends AbstractSpringTest {

    @Autowired
    private ActivityManager manager;

    @BeforeClass
    public static void setup() {
        initSysProperty();
    }

    @Test
    public void getByIdTest() {
        manager.getById(100);
    }

    @Test
    public void list() {
        manager.list();
    }

    @Test
    public void multiList() {
        manager.list();
        manager.list();
        manager.getById(100);
    }
}