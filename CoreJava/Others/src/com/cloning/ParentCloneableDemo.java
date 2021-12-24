package com.cloning;

/**
 * Demo class to test if clone() works in case when parent class implements Cloneable
 * but child class does not implement Cloneable.
 * 
 * It works!
 * 
 */
public class ParentCloneableDemo {

  public static void main(String[] args) throws CloneNotSupportedException {
    Employee employee = new Employee();
    employee.setFirstName("Nilesh");
    employee.setLastName("Wani");
    employee.setSalary(1000);

    System.out.println("Original employee: " + employee);

    Employee clonedEmployee = (Employee) employee.clone();
    System.out.println("Cloned employee: " + clonedEmployee);
  }

  private static class Person implements Cloneable {
    private String firstName;
    private String lastName;

    public Person() {
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }
  }

  private static class Employee extends Person {
    private int salary;

    public int getSalary() {
      return salary;
    }

    public void setSalary(int salary) {
      this.salary = salary;
    }

    @Override
    public String toString() {
      return "Employee [salary=" + getSalary() + ", firstName()=" + getFirstName() + ", lastName()=" + getLastName() + "]";
    }

  }
}
