package org.spring.homeWork2.beans;

public class BeanD extends Bean {

  public BeanD(String name, int value) {
    super(name, value);
  }

  public void initD() {
    System.out.println("init metod beanD");
  }

  public void destroyD() {
    System.out.println("destroy metod beanD");
  }
}
