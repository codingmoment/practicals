package com.demo.employee.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.employee.domain.Employee;

@CacheConfig(cacheNames = "tenMinuteCache")
@Service
public class EmployeeService {

  private Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();

  @PostConstruct
  public void initializeData() {
    List<Employee> employees = Arrays.asList(
                                    new Employee(100L, "John", "Selby"),
                                    new Employee(200L, "George", "Harris"),
                                    new Employee(300L, "Henry", "Charlwood"),
                                    new Employee(400L, "Billy", "Barnes"),
                                    new Employee(500L, "Frank", "Penn")
                                );
    employeeMap = employees.stream().collect(Collectors.toMap(Employee::getEmployeeId, Function.identity()));
  }

  @Cacheable
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

  @Cacheable("twentyMinuteCache")
  public Employee getEmployeeByFirstName(String firstName) {
    try {
      // Sleeping the thread for 2 seconds to simulate DB call.
      // Actually service should send request to repository which then interacts with DB.
      Thread.sleep(2000);
    }
    catch (InterruptedException e) {
    }

    return employeeMap.values().stream().filter(employee -> employee.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElse(null);
  }

}
