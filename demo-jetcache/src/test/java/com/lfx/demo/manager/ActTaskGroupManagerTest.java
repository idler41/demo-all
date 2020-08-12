package com.lfx.demo.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lfx.demo.AbstractSpringTest;
import com.lfx.demo.entity.ActTask;
import com.lfx.demo.entity.ActTaskGroup;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-17 13:47:50
 */
public class ActTaskGroupManagerTest extends AbstractSpringTest {

    @Autowired
    private ActTaskGroupManager actTaskGroupManager;

    @Autowired
    private ActTaskManager actTaskManager;

    private ActTaskGroup actTaskGroup;

    private List<ActTask> actTaskList;

    @BeforeClass
    public static void init() {
        initSysProperty();
    }

//    @Before
    public void before() {
        actTaskGroup = readJsonFile("actTaskGroup.json", ActTaskGroup.class);
        //noinspection unchecked
        actTaskList = readJsonFile("actTaskList.json", new TypeReference<List<ActTask>>() {
        });

        actTaskGroupManager.removeById(actTaskGroup.getId());
        actTaskGroupManager.save(actTaskGroup);

        actTaskList.stream().filter(Objects::nonNull).forEach(item -> {
            actTaskManager.removeById(item);
            actTaskManager.save(item);
        });
    }

//    @After
    public void after() {
//        actTaskGroupManager.removeById(actTaskGroup.getId());
//        actTaskList.stream().filter(Objects::nonNull).forEach(item -> {
//            actTaskManager.removeById(item);
//        });
    }

    @Test
    public void getById() {
        System.out.println(toJsonStr(actTaskGroupManager.getById(10), true));
        System.out.println(toJsonStr(actTaskManager.getById(10), true));
        System.out.println(toJsonStr(actTaskManager.getById(10), true));

    }

    @Test
    public void updateCascadeById() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            for (; ; ) {
                System.out.println("cache task actId:" + actTaskManager.getById(actTaskList.get(0).getId()).getActId());
                System.out.println("cache taskGroup actId:" + actTaskGroupManager.getById(actTaskGroup.getId()).getActId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        actTaskGroup.setActId(actTaskGroup.getActId() + 1);
        actTaskList.stream().filter(Objects::nonNull).forEach(item -> {
            item.setActId(item.getActId() + 1);
        });

        try {
            actTaskGroupManager.updateGroupAndTaskList(actTaskGroup, actTaskList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(5000);
    }
}
