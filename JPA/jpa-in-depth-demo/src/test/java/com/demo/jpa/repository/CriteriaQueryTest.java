package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.entity.Course;

@SpringBootTest
class CriteriaQueryTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CriteriaQueryTest.class);

	@Autowired
	private EntityManager entityManager;

	@Test
	void findAllCoursesUsingCriteriaQuery() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> root = cq.from(Course.class);

		TypedQuery<Course> typedQuery = entityManager.createQuery(cq.select(root));
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findCoursesWithSpringUsingCriteriaQuery() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> root = cq.from(Course.class);
		Predicate likeSpring = cb.like(root.get("name"), "%Spring%");
		cq.where(likeSpring);
		
		TypedQuery<Course> typedQuery = entityManager.createQuery(cq.select(root));
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findCoursesWithNoStudentsUsingCriteriaQuery() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> root = cq.from(Course.class);
		Predicate emptyStudents = cb.isEmpty(root.get("students"));
		cq.where(emptyStudents);
		
		TypedQuery<Course> typedQuery = entityManager.createQuery(cq.select(root));
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findCoursesJoinStudentsUsingCriteriaQuery() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> root = cq.from(Course.class);
		root.join("students");
		
		TypedQuery<Course> typedQuery = entityManager.createQuery(cq.select(root));
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}

	@Test
	void findCoursesLeftJoinStudentsUsingCriteriaQuery() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> root = cq.from(Course.class);
		root.join("students", JoinType.LEFT);
		
		TypedQuery<Course> typedQuery = entityManager.createQuery(cq.select(root));
		List<Course> courses = typedQuery.getResultList();
		LOGGER.info("Courses: {}", courses);
		assertFalse(courses.isEmpty());
	}
}
