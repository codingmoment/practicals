package com.demo.controller.employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.controller.employee.domain.Employee;
import com.demo.controller.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
  
  @Autowired
  private EmployeeService employeeService;
  
  @GetMapping("/{employeeId:[0-9]\\d*}")
  public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) {
    return employeeService.getEmployeeById(employeeId);
  }
  
  @GetMapping(params = {"firstName"}, value = "/search")
  public Employee searchEmployeeByFirstName(@RequestParam(name = "firstName") String firstName) {
    return employeeService.searchEmployeeByFirstName(firstName);
  }
  
  @PostMapping
  public Employee insertEmployee(@RequestBody @Valid Employee employee) {
    return employeeService.insertEmployee(employee);
  }
}
