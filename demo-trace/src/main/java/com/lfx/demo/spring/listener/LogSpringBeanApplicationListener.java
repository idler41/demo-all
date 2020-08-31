package com.lfx.demo.spring.listener;

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
public class LogSpringBeanApplicationListener implements SmartApplicationListener {

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
        if (Boolean.parseBoolean(event.getApplicationContext().getEnvironment().getProperty("app.config.print.bean"))) {
            logSpringBean(event);
        }
    }

    private void logSpringBean(ApplicationStartedEvent event) {
        StringBuilder allBeanNameStr = new StringBuilder(500);
        allBeanNameStr.append("----------------- spring beans start -----------------")
                .append(System.lineSeparator());
        String[] beanNames = event.getApplicationContext().getBeanDefinitionNames();
        allBeanNameStr.append("beans amount: ").append(beanNames.length)
                .append(System.lineSeparator());
        Arrays.sort(beanNames);
        boolean printBeanImpl = Boolean.parseBoolean(event.getApplicationContext().getEnvironment().getProperty("app.config.print.bean-impl"));
        for (String name : beanNames) {
            allBeanNameStr.append(name);
            if (printBeanImpl) {
                allBeanNameStr.append("=").append(event.getApplicationContext().getBean(name));
            }
            allBeanNameStr.append(System.lineSeparator());
        }
        allBeanNameStr.append("----------------- spring beans end -----------------");
        log.info(allBeanNameStr.toString());
    }
}
