package com.devmango.spring.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.devmango.spring.employee.domain.Employee;

@Service
public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	private Long employeeIdCounter = 0L;
	private List<Employee> employeeList = new ArrayList<>();

	/**
	 * Creates new employee.
	 * 
	 * @param employee - The employee details
	 * @return The created employee
	 */
	public Employee createEmployee(Employee employee) {
		LOGGER.info("Creating new employee");
		employee.setEmployeeId(++employeeIdCounter);
		employeeList.add(employee);
		return employee;
	}

	/**
	 * Updates an existing employee.
	 * 
	 * @param employeeId - The employee Id
	 * @param employee - New employee details
	 * @return The updated employee. Null in case employee did not exist.
	 */
	public Employee updateEmployee(Long employeeId, Employee employee) {
		LOGGER.info("Updating an employee");
		boolean updated = false;
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getEmployeeId().equals(employeeId)) {
				employee.setEmployeeId(employeeId);
				employeeList.set(i, employee);
				updated = true;
				break;
			}
		}
		return updated ? employee : null;
	}

	/**
	 * Gets all employees.
	 * 
	 * @return List of all employees
	 */
	public List<Employee> getAllEmployees() {
		LOGGER.info("Get all employees");
		return employeeList;
	}

	/**
	 * Finds employee for given employee id
	 * 
	 * @param employeeId - Employee id to search
	 * @return Employee for the given employee id
	 */
	public Employee findEmployeesById(Long employeeId) {
		LOGGER.info("Finding an employee by ID");
		for(Employee e: employeeList) {
			if(e.getEmployeeId().equals(employeeId)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Finds employees for given first name
	 * 
	 * @param firstName - First name to search employees
	 * @return List of matching employees
	 */
	public List<Employee> findEmployeesByFirstName(String firstName) {
		LOGGER.info("Finding employees by first name");
		return employeeList.stream().filter(e -> e.getFirstName().toLowerCase().contains(firstName.toLowerCase())).collect(Collectors.toList());
	}
	
	/**
	 * Deletes employee for given employee Id
	 * 
	 * @param employeeId - Id of employee to be deleted
	 */
	public void deleteEmployee(Long employeeId) {
		LOGGER.info("Deleting an employee");
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getEmployeeId().equals(employeeId)) {
				employeeList.remove(i);
				break;
			}
		}
	}
}
