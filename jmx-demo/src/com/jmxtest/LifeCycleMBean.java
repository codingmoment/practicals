package com.jmxtest;

public interface LifeCycleMBean
{
  public int getAge();

  public void setAge(int age);

  public void kill();

  public boolean isAlive();

  public String getName();

  public void setName(String name);

  public void printAge();
}
