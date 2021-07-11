package com.jmxtest;

public class LifeCycle implements LifeCycleMBean
{
  private int age;
  private boolean isAlive = true;
  private String name;

  public LifeCycle(String name, int age)
  {
    this.name = name;
    this.age = age;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public void kill()
  {
    isAlive = false;
  }

  public boolean isAlive()
  {
    return isAlive;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void printAge()
  {
    System.out.println("Current Age is = " + age);
  }
}
