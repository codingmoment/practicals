package com.devmango.spring.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.spring.employee.domain.Employee;
import com.devmango.spring.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping(path = "/{employeeId}")
	public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
		return employeeService.updateEmployee(employeeId, employee);
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping(path = "/{employeeId}")
	public Employee getEmployee(@PathVariable Long employeeId) {
		return employeeService.findEmployeesById(employeeId);
	}

	@GetMapping("/search")
	public List<Employee> findEmployeesByFirstName(@RequestParam("firstName") String firstName) {
		return employeeService.findEmployeesByFirstName(firstName);
	}

	@DeleteMapping(path = "/{employeeId}")
	public void deleteEmployee(@PathVariable Long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}

}
