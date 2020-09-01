package com.lfx.demo.spring.listener;

import com.lfx.demo.spring.config.BeanPrintProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

import java.util.Arrays;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-08-31 18:07:22
 */
@Slf4j
public class BeanPrintApplicationListener implements SmartApplicationListener {

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationStartedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ApplicationStartedEvent event = (ApplicationStartedEvent) applicationEvent;
        if (event.getApplicationContext().getBean(BeanPrintProperties.class).isPrintBean()) {
            logSpringBean(event);
        }
    }

    private void logSpringBean(ApplicationStartedEvent event) {
        StringBuilder allBeanNameStr = new StringBuilder(500);
        allBeanNameStr.append("----------------- spring beans start -----------------")
                .append(System.lineSeparator());
        String[] beanNames = event.getApplicationContext().getBeanDefinitionNames();
        Arrays.sort(beanNames);
        boolean printBeanImpl = event.getApplicationContext().getBean(BeanPrintProperties.class).isPrintBeanImpl();
        for (String name : beanNames) {
            allBeanNameStr.append(name);
            if (printBeanImpl) {
                allBeanNameStr.append("=").append(event.getApplicationContext().getBean(name));
            }
            allBeanNameStr.append(System.lineSeparator());
        }
        allBeanNameStr.append("beans amount: ").append(beanNames.length)
                .append(System.lineSeparator());
        allBeanNameStr.append("----------------- spring beans end -----------------");
        allBeanNameStr.append(System.lineSeparator()).append("################ 服务启动成功 ################");
        log.info(allBeanNameStr.toString());
    }
}
