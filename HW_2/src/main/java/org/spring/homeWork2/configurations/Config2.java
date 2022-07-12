package org.spring.homeWork2.configurations;

import org.spring.homeWork2.beans.BeanB;
import org.spring.homeWork2.beans.BeanC;
import org.spring.homeWork2.beans.BeanD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@Import(Config1.class)
@PropertySource("classpath:values.properties")
public class Config2 {

  @Autowired Environment environment;

  @Bean(initMethod = "initB", destroyMethod = "destroyB")
  @DependsOn("beanD")
  public BeanB beanB(
      @Value("${beanB.name}") final String name, @Value("${beanB.value}") final int value) {
    return new BeanB(name, value);
  }

  @Bean(initMethod = "initC", destroyMethod = "destroyC")
  @DependsOn("beanB")
  BeanC beanC(@Value("${beanC.name}") final String name, @Value("${beanC.value}") final int value) {
    return new BeanC(name, value);
  }

  @Bean(initMethod = "initD", destroyMethod = "destroyD")
  BeanD beanD(@Value("${beanD.name}") final String name, @Value("${beanD.value}") final int value) {
    return new BeanD(name, value);
  }
}
