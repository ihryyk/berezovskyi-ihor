package org.spring.homeWork2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE extends Bean {
  public BeanE() {
    super();
  }

  @PostConstruct
  public void postConstrict() {
    System.out.println("BeanE method PostConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("BeanE method PreDestroy");
  }
}
