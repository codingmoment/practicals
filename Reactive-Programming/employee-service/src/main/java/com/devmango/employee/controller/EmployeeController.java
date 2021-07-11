package com.devmango.employee.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.employee.domain.Leave;
import com.devmango.employee.domain.Task;
import com.devmango.employee.service.EmployeeService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	private EmployeeService bookingService;

	public EmployeeController(EmployeeService employeeService) {
		this.bookingService = employeeService;
	}

	@GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Task> getAllEmployeesTasks() {
		return bookingService.getAllEmployeesTasks();
	}

	@GetMapping(value = "/tasks/reactively", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Task> getAllEmployeesTasksReactively() {
		return bookingService.getAllEmployeesTasksReactively();
	}

	@GetMapping(value = "/tasks/reactively/multithread", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Task> getAllEmployeesTasksReactivelyMultiThreaded() {
		return bookingService.getAllEmployeesTasksReactivelyMultiThreaded();
	}

	@GetMapping(value = "/leaves", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Leave> getAllEmployeesLeaves() {
		return bookingService.getAllEmployeesLeaves();
	}
}
