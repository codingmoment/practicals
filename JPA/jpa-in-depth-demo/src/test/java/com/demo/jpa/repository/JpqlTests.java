package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.entity.Course;
import com.demo.jpa.entity.Student;

@SpringBootTest
class JpqlTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpqlTests.class);

	@Autowired
	private EntityManager entityManager;

	@Test
	void findAllUsingBasicQueryShouldReturnRecords() {
		Query query = entityManager.createQuery("SELECT c FROM Course c");
		List<Course> courses = query.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findAllUsingTypedQueryShouldReturnRecords() {
		TypedQuery<Course> typedQuery = entityManager.createQuery("SELECT c FROM Course c", Course.class);
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findAllUsingNamedQueryShouldReturnRecords() {
		TypedQuery<Course> typedQuery = entityManager.createNamedQuery("get_all_courses", Course.class);
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findCoursesWithoutStudent() {
		TypedQuery<Course> typedQuery = entityManager.createQuery("SELECT c FROM Course c WHERE c.students is empty",
				Course.class);
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(false);
	}

	@Test
	void findCoursesWithAtLeast2Student() {
		TypedQuery<Course> typedQuery = entityManager.createQuery("SELECT c FROM Course c WHERE size(c.students) >=2",
				Course.class);
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(false);
	}

	@Test
	void findCoursesOrderByStudent() {
		TypedQuery<Course> typedQuery = entityManager
				.createQuery("SELECT c FROM Course c ORDER BY size(c.students) DESC", Course.class);
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(false);
	}

	@Test
	void findStudentsHaving1234InPassport() {
		TypedQuery<Student> typedQuery = entityManager
				.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%1234%'", Student.class);
		List<Student> students = typedQuery.getResultList();
		LOGGER.info("Students: {}", students);
		assertFalse(false);
	}

	@Test
	void fetchCoursesAndStudentsUsingJoin() {
		Query query = entityManager.createQuery("SELECT c, s FROM Course c JOIN c.students s");
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			LOGGER.info("Course: {} Student: {}", result[0], result[1]);
		}
		assertFalse(results.isEmpty());
	}

	@Test
	void fetchCoursesAndStudentsUsingLeftJoin() {
		Query query = entityManager.createQuery("SELECT c, s FROM Course c LEFT JOIN c.students s");
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			LOGGER.info("Course: {} Student: {}", result[0], result[1]);
		}
		assertFalse(results.isEmpty());
	}

	@Test
	void fetchCoursesAndStudentsUsingCrossJoin() {
		Query query = entityManager.createQuery("SELECT c, s FROM Course c, Student s");
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			LOGGER.info("Course: {} Student: {}", result[0], result[1]);
		}
		assertFalse(results.isEmpty());
	}
}
