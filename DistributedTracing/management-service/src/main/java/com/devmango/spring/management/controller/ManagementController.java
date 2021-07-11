package com.devmango.spring.management.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.spring.management.domain.Employee;
import com.devmango.spring.management.service.ManagementService;

@RestController
@RequestMapping(value = "/management")
public class ManagementController {

	private ManagementService managementService;

	public ManagementController(ManagementService managementService) {
		this.managementService = managementService;
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return managementService.createEmployee(employee);
	}

	@PutMapping(path = "/employees/{employeeId}")
	public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
		return managementService.updateEmployee(employeeId, employee);
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
	  Employee e = new Employee();
	  e.setFirstName("Nilesh");
	  e.setLastName("Wani");
	  managementService.createEmployee(e);
    e.setFirstName("Shailesh");
    e.setLastName("Wani");
	  managementService.createEmployee(e);
    e.setFirstName("Kamlesh");
    e.setLastName("Wani");
	  managementService.createEmployee(e);
		return managementService.getAllEmployees();
	}

	@GetMapping(path = "/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Long employeeId) {
		return managementService.findEmployeesById(employeeId);
	}

	@GetMapping("/employees/search")
	public List<Employee> findEmployeesByFirstName(@RequestParam("firstName") String firstName) {
		return managementService.findEmployeesByFirstName(firstName);
	}

	@DeleteMapping(path = "/employees/{employeeId}")
	public void deleteEmployee(@PathVariable Long employeeId) {
		managementService.deleteEmployee(employeeId);
	}

	@PostMapping("/salary/process/fixed/{employeeId}")
	public void processFixedSalary(@PathVariable Long employeeId) {
		managementService.processFixedSalary(employeeId);
	}

	@PostMapping("/salary/process/cached/{employeeId}")
	public void processCachedSalary(@PathVariable Long employeeId) {
		managementService.processCachedSalary(employeeId);
	}

	@PostMapping("/salary/process/async/{employeeId}")
	public void processAsyncSalary(@PathVariable Long employeeId) {
		managementService.processAsyncSalary(employeeId);
	}

	@PostMapping("/salary/process/asynchold/{employeeId}")
	public void processAsyncHoldSalary(@PathVariable Long employeeId) throws InterruptedException, ExecutionException {
		CompletableFuture<Long> future = managementService.processAsyncSalary(employeeId);
		future.get();
	}

}
