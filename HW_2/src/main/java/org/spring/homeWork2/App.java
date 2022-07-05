package org.spring.homeWork2;

import org.spring.homeWork2.beans.BeanF;
import org.spring.homeWork2.configurations.Config2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(Config2.class);
    System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
    context.getBean("beanF", BeanF.class);
    context.close();
  }
}
