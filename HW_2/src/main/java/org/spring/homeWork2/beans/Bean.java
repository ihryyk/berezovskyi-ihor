package org.spring.homeWork2.beans;

import org.springframework.stereotype.Component;

@Component
public class Bean {

  private String name;

  private int value;

  public Bean() {}

  public Bean(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Bean{" + "name='" + name + '\'' + ", value=" + value + '}';
  }
}
