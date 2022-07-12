package org.spring.homeWork2.beans;

public class BeanC extends Bean {

  public BeanC(String name, int value) {
    super(name, value);
  }

  public void initC() {
    System.out.println("init metod beanC");
  }

  public void destroyC() {
    System.out.println("destroy metod beanC");
  }
}
