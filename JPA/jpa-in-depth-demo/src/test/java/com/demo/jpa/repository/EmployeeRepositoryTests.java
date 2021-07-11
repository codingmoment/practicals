package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.h2.tools.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.entity.Employee;
import com.demo.jpa.entity.FullTimeEmployee;
import com.demo.jpa.entity.PartTimeEmployee;

@SpringBootTest
class EmployeeRepositoryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryTests.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@BeforeAll
	public static void init() throws SQLException {
		Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
	}

	@AfterAll
	public static void destroy() throws SQLException {
		Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").stop();
	}

	@Test
	void createDifferentTypesOfEmployees() {
		Employee partTimeEmployee = new PartTimeEmployee("Nilesh Wani", BigDecimal.valueOf(100));
		Employee fullTimeEmployee = new FullTimeEmployee("Shailesh Wani", BigDecimal.valueOf(10000));

		employeeRepository.save(partTimeEmployee);
		employeeRepository.save(fullTimeEmployee);

		LOGGER.info("All Employees -> {}", employeeRepository.findAll());

		assertTrue(true);
	}
}
