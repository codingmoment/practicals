package com.devmango.spring.management.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.devmango.spring.management.domain.Employee;

@Component
public class EmployeeRestTemplate {

	private RestTemplate restTemplate = new RestTemplate();

	private final String URI = "http://localhost:8080/employees";

	public Employee createEmployee(Employee employee) {
		return restTemplate.postForObject(URI, employee, Employee.class);
	}

	public List<Employee> getAllEmployees() {
		ResponseEntity<Employee[]> employeeEntity = restTemplate.getForEntity(URI, Employee[].class);
		return Arrays.asList(employeeEntity.getBody());
	}

	public void updateEmployee(Long employeeId, Employee employee) {
		String uri = URI + "/{employeeId}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("employeeId", String.valueOf(employeeId));

		restTemplate.put(uri, employee, Employee.class, params);
	}

	public Employee getEmployee(Long employeeId) {
		String uri = URI + "/{employeeId}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("employeeId", String.valueOf(employeeId));

		return restTemplate.getForEntity(uri, Employee.class, params).getBody();
	}

	public void deleteEmployee(Long employeeId) {
		String uri = URI + "/{employeeId}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("employeeId", String.valueOf(employeeId));

		restTemplate.delete(uri, params);
	}
}
