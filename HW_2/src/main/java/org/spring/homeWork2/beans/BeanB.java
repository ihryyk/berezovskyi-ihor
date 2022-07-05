package org.spring.homeWork2.beans;

public class BeanB extends Bean {

  public BeanB(String name, int value) {
    super(name, value);
  }

  public void newInitMethodForBeanB() {
    System.out.println("New init metod beanB");
  }

  public void initB() {
    System.out.println("init metod beanB");
  }

  public void destroyB() {
    System.out.println("destroy metod beanB");
  }
}
