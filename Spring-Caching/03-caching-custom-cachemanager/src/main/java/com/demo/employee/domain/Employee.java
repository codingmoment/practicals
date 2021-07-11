package com.demo.employee.domain;

import java.io.Serializable;

public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long employeeId;
  private String firstName;
  private String lastName;

  public Employee(Long employeeId, String firstName, String lastName) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
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

}
