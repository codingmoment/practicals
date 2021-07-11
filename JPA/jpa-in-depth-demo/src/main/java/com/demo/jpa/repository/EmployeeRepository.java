package com.demo.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.jpa.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Employee findById(int id) {
		return entityManager.find(Employee.class, id);
	}

	public List<Employee> findAll() {
		return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}

	public Employee save(Employee employee) {
		if (employee.getId() == null) {
			entityManager.persist(employee);
		} else {
			employee = entityManager.merge(employee);
		}
		return employee;
	}
}
