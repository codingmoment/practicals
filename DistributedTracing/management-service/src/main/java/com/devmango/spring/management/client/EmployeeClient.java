package com.devmango.spring.management.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.devmango.spring.management.domain.Employee;

@FeignClient(name = "EMPLOYEE-SERVICE", url = "http://localhost:8080/employees")
public interface EmployeeClient {

	@PostMapping
	Employee createEmployee(@RequestBody Employee employee);

	@PutMapping(path = "/{employeeId}")
	Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee);

	@GetMapping
	List<Employee> getAllEmployees();

	@GetMapping(path = "/{employeeId}")
	Employee getEmployee(@PathVariable Long employeeId);

	@GetMapping(path = "/search")
	List<Employee> findEmployeesByFirstName(@RequestParam("firstName") String firstName);

	@DeleteMapping(path = "/{employeeId}")
	void deleteEmployee(@PathVariable Long employeeId);
}
