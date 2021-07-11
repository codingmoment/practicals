package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.entity.Course;

@SpringBootTest
class NativeQueriesTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(NativeQueriesTests.class);

	@Autowired
	private EntityManager entityManager;

	@Test
	void findAllUsingNativeQueryShouldReturnRecords() {
		Query query = entityManager.createNativeQuery("SELECT * FROM Course c", Course.class);
		List<Course> courses = query.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findByIdUsingNativeQueryShouldReturnRecords() {
		Query query = entityManager.createNativeQuery("SELECT * FROM Course c WHERE id = ?", Course.class);
		query.setParameter(1, 100);
		Course course = (Course)query.getSingleResult();
		assertEquals("Spring JPA", course.getName());
	}
}
