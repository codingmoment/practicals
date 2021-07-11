package com.demo.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.domain.Employee;
import com.demo.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
  
  @Autowired
  private EmployeeService employeeService;
  
  @GetMapping("/{employeeId:[0-9]\\d*}")
  public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) {
    return employeeService.getEmployeeById(employeeId);
  }
}
