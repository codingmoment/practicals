package com.demo.employee.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.employee.domain.Employee;

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

  @Cacheable("employeeCache")
  public Employee getEmployeeById(Long employeeId) {
    try {
      // Sleeping the thread for 2 seconds to simulate DB call.
      // Actually service should send request to repository which then interacts with DB.
      Thread.sleep(2000);
    }
    catch (InterruptedException e) {
    }

    return employeeMap.get(employeeId);
  }
}
