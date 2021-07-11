package com.demo.controller.employee.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long employeeId;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;

  public Employee() {
  }

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
