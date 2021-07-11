package com.devmango.spring.management.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.devmango.spring.management.client.EmployeeClient;
import com.devmango.spring.management.domain.Employee;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;

@Service
public class ManagementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagementService.class);

	@Autowired
	private EmployeeClient employeeClient;
	@Autowired
	private ExecutorService cacheExecutorService;
	@Autowired
	private ExecutorService fixedExecutorService;
	
	@Autowired
	private Tracer tracer;

	public Employee createEmployee(Employee employee) {
		LOGGER.info("Creating new employee");
		return employeeClient.createEmployee(employee);
	}

	public Employee updateEmployee(Long employeeId, Employee employee) {
		LOGGER.info("Updating an employee");
		return employeeClient.updateEmployee(employeeId, employee);
	}

	public List<Employee> getAllEmployees() {
		//Span allEmployeeSpan = tracer.newChild(tracer.currentSpan().context());
		//Span allEmployeeSpan = tracer.nextSpan();
		ScopedSpan allEmployeeSpan = tracer.startScopedSpan("fetchAllEmployee");
		allEmployeeSpan.tag("management.getallemployees.key", "fetchAllEmployee");
		//allEmployeeSpan.start();
		LOGGER.info("Get all employees");
		List<Employee> allEmployees = employeeClient.getAllEmployees();
		allEmployeeSpan.finish();
		return allEmployees;
	}

	public Employee findEmployeesById(Long employeeId) {
		LOGGER.info("Finding an employee by ID");
		return employeeClient.getEmployee(employeeId);
	}

	public List<Employee> findEmployeesByFirstName(String firstName) {
		LOGGER.info("Finding employees by first name");
		return employeeClient.findEmployeesByFirstName(firstName);
	}

	public void deleteEmployee(Long employeeId) {
		LOGGER.info("Deleting an employee");
		employeeClient.deleteEmployee(employeeId);
	}

	public void processFixedSalary(Long employeeId) {
		fixedExecutorService.submit(() -> {
			LOGGER.info("{} - processSalary for employee {} started.", Thread.currentThread().getName(), employeeId);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
			LOGGER.info("{} - processSalary for employee {} completed.", Thread.currentThread().getName(), employeeId);
		});
	}

	public void processCachedSalary(Long employeeId) {
		cacheExecutorService.submit(() -> {
			LOGGER.info("{} - processSalary for employee {} started.", Thread.currentThread().getName(), employeeId);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
			LOGGER.info("{} - processSalary for employee {} completed.", Thread.currentThread().getName(), employeeId);
		});
	}

    @Async("workExecutor")
    public CompletableFuture<Long> processAsyncSalary(Long employeeId) {
		LOGGER.info("{} - processSalary for employee {} started.", Thread.currentThread().getName(), employeeId);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		LOGGER.info("{} - processSalary for employee {} completed.", Thread.currentThread().getName(), employeeId);
		return CompletableFuture.completedFuture(employeeId);
    }
}
