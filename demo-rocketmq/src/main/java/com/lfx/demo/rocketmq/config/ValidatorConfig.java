package com.lfx.demo.rocketmq.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-23 09:03:38
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator(AutowireCapableBeanFactory springFactory) {
        try (ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(springFactory)).buildValidatorFactory()) {
            return factory.getValidator();
        }
    }
}
