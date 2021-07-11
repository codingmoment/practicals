package com.demo.controller.employee.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.controller.employee.domain.Employee;

@Service
public class EmployeeService {

  private Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();

  {
    employeeMap.put(100L, new Employee(100L, "John", "Selby"));
    employeeMap.put(200L, new Employee(200L, "George", "Harris"));
    employeeMap.put(300L, new Employee(300L, "Henry", "Charlwood"));
    employeeMap.put(400L, new Employee(400L, "Billy", "Barnes"));
    employeeMap.put(500L, new Employee(500L, "Frank", "Penn"));
  }

  public Employee getEmployeeById(Long employeeId) {
    return employeeMap.get(employeeId);
  }

  public Employee searchEmployeeByFirstName(@RequestParam(name = "firstName") String firstName) {
    return employeeMap.values().stream().filter(employee -> employee.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElse(null);
  }
  
  public Employee insertEmployee(@RequestBody Employee employee) {
    employee.setEmployeeId(0L);
    employeeMap.keySet().forEach(employeeId -> employee.setEmployeeId(employeeId > employee.getEmployeeId() ? employeeId : employee.getEmployeeId()));
    employee.setEmployeeId(employee.getEmployeeId() + 100);
    employeeMap.put(employee.getEmployeeId(), employee);
    return employee;
  }
}
