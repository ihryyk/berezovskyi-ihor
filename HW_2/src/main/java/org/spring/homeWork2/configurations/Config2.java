package org.spring.homeWork2.configurations;

import org.spring.homeWork2.beans.BeanB;
import org.spring.homeWork2.beans.BeanC;
import org.spring.homeWork2.beans.BeanD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@Import(Config1.class)
@PropertySource("classpath:values.properties")
public class Config2 {
    @Autowired
    Environment environment;

    @Bean(initMethod = "initB", destroyMethod = "destroyB")
    @DependsOn("beanD")
    BeanB beanB (){
        return new BeanB(environment.getProperty("beanB.name"), Integer.parseInt(environment.getProperty("beanB.value")));
    }

    @Bean(initMethod = "initC", destroyMethod = "destroyC")
    @DependsOn("beanB")
    BeanC beanC (){
        return new BeanC(environment.getProperty("beanC.name"), Integer.parseInt(environment.getProperty("beanC.value")));
    }

    @Bean(initMethod = "initD", destroyMethod = "destroyD")
    BeanD beanD (){
        return new BeanD(environment.getProperty("beanD.name"), Integer.parseInt(environment.getProperty("beanD.value")));
    }
}
