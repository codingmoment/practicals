package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.entity.Course;
import com.demo.jpa.entity.Rating;
import com.demo.jpa.entity.Review;

@SpringBootTest
class CourseRepositoryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTests.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@Transactional
	void findById100ShouldGiveSpringJPA() {
		Course course = courseRepository.findById(100);
		List<Review> reviews = course.getReviews();
		LOGGER.info("Reviews -> {}", reviews);
		assertEquals("Spring JPA", course.getName());
	}

	@Test
	void deleteByIdShouldRemoveCourse() {
		courseRepository.deleteById(400);
		assertNull(courseRepository.findById(400));
	}

	@Test
	void saveShouldUpdateCourse() {
		Course course = courseRepository.findById(300);
		course.setName("ReactJS 2.0");
		courseRepository.save(course);
		course = courseRepository.findById(300);
		assertEquals("ReactJS 2.0", course.getName());
	}

	@Test
	void saveShouldCreateCourse() {
		Course course = new Course("Microservices");
		course = courseRepository.save(course);
		course = courseRepository.findById(course.getId());
		assertEquals("Microservices", course.getName());
	}

	@Test
	@Transactional
	void addReviewsUsingOneToManyRelationship() {

		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review(Rating.FIVE, "Nice explanation."));
		reviews.add(new Review(Rating.FIVE, "Perfect!"));

		courseRepository.addReviewsForCourse(300, reviews);

		Course course = courseRepository.findById(300);
		assertEquals(3, course.getReviews().size());
	}

	@Test
	void callsShouldBeCachedInSecondLevelCache() {
		Course course = courseRepository.findById(100);
		LOGGER.info("Course 1st call -> {}", course);
		course = courseRepository.findById(100);
		LOGGER.info("Course 2nd call -> {}", course);
		assertEquals("Spring JPA", course.getName());
	}

	@Test
	@Transactional
	void fetchingStudentsLaziliy() {
		List<Course> courses = entityManager.createNamedQuery("get_all_courses", Course.class).getResultList();
		for (Course course : courses) {
			LOGGER.info("Course: {} has students: {}", course, course.getStudents());
		}
		assertTrue(true);
	}

	@Test
	@Transactional
	void fetchingStudentsEagerly() {
		EntityGraph<Course> courseGraph = entityManager.createEntityGraph(Course.class);
		courseGraph.addSubgraph("students");

		List<Course> courses = entityManager.createNamedQuery("get_all_courses", Course.class).setHint("javax.persistence.loadgraph", courseGraph).getResultList();
		for (Course course : courses) {
			LOGGER.info("Course: {} has students: {}", course, course.getStudents());
		}
		assertTrue(true);
	}

}
